package com.accentrix.hku.util;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.api.UITabPanel;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.accentrix.hku.constant.ConstantCommon;

public class UIUtil {
    private static final Logger LOG = LoggerFactory.getLogger(UIUtil.class);

    private UIUtil() {
    }

    public static void update(String path) {
        update(path, true);
    }

    public static void update(String path, boolean addRootClientId) {
        RequestContext rc = RequestContext.getCurrentInstance();
        if (rc == null) {
            LOG.warn("RequestContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }

        if (addRootClientId) {
            String rootClientId = getRootClientId();
            LOG.debug("rootClientId: {}", rootClientId);

            rc.update(rootClientId + ":" + path);
        } else {
            rc.update(path);
        }

    }

    public static void hide(String widget) {
        execute("PF('" + widget + "').hide();");
    }

    public static void show(String widget) {
        execute("PF('" + widget + "').show();");
    }

    /**
     * UIUtil.select(widget, index, true)
     */
    public static void select(String widget, int index) {
        select(widget, index, true);
    }

    public static void select(String widget, int index, boolean silent) {
        execute("PF('" + widget + "').select(" + index + ", " + silent + ")");
    }

    public static void addErrorMessage(String path, String summary) {
        addErrorMessage(path, summary, null);
    }

    public static void addErrorMessage(String path, String summary, String detail) {
        addMessage(path, summary, detail, FacesMessage.SEVERITY_ERROR);
    }

    public static void addInfoMessage(String path, String msg) {
        addMessage(path, msg, null, FacesMessage.SEVERITY_INFO);
    }

    public static void addWarnMessage(String path, String msg) {
        addWarnMessage(path, msg, null);
    }

    public static void addWarnMessage(String path, String summary, String detail) {
        addMessage(path, summary, detail, FacesMessage.SEVERITY_WARN);
    }

    public static void addFatalMessage(String path, String msg) {
        addMessage(path, msg, null, FacesMessage.SEVERITY_FATAL);
    }

    public static void reset(String path) {
        RequestContext rc = RequestContext.getCurrentInstance();
        if (rc == null) {
            LOG.warn("RequestContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        rc.reset(path);
    }

    public static String setInvalid(String path) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return null;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return null;
        }

        UIComponent component = viewRoot.findComponent(path);
        setValid(component, false);
        return component.getId();
    }

    public static String setValid(String path) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return null;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return null;
        }

        UIComponent component = viewRoot.findComponent(path);
        setValid(component, true);
        return component.getId();
    }

    public static void execute(String script) {
        RequestContext rc = RequestContext.getCurrentInstance();
        if (rc == null) {
            LOG.warn("RequestContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        rc.execute(script);
    }

    private static void addMessage(String path, String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(summary, detail);
        message.setSeverity(severity);

        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }

        if (path != null) {
            UIViewRoot viewRoot = fc.getViewRoot();
            if (viewRoot == null) {
                LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
                return;
            }

            UIComponent component = viewRoot.findComponent(path);
            if (!FacesMessage.SEVERITY_INFO.equals(severity)) {
                try {
                    BeanUtils.setProperty(component, "valid", false);
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
            }

            String clientId = component.getClientId();

            fc.addMessage(clientId, message);
        } else {
            fc.addMessage(null, message);
        }
    }

    private static String getRootClientId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return null;
        }

        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return null;
        }

        return viewRoot.getClientId();
    }

    private static void setValid(UIComponent component, boolean valid) {
        try {
            BeanUtils.setProperty(component, "valid", valid);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public static boolean validate(Object validatable) {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Set<ConstraintViolation<Object>> set = validator.validate(validatable);

        if (set.isEmpty()) {
            return true;
        }

        for (ConstraintViolation<Object> constraintViolation : set) {
            String msg = constraintViolation.getMessage();

            UIUtil.addErrorMessage(null, msg);
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    public static void resetDataTableSortBy(String path) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return;
        }

        UIComponent component = viewRoot.findComponent(path);
        Assert.notNull(component);
        Assert.isInstanceOf(DataTable.class, component);
        DataTable dataTable = (DataTable) component;
        dataTable.setValueExpression("sortBy", null);
    }

    @SuppressWarnings("deprecation")
    public static void resetDataTable(String path) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return;
        }

        UIComponent component = viewRoot.findComponent(path);
        Assert.notNull(component);
        Assert.isInstanceOf(DataTable.class, component);
        DataTable dataTable = (DataTable) component;
        dataTable.reset();
    }

    @SuppressWarnings("deprecation")
    public static void resetDataTableSort(String path, String sortOrder) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return;
        }

        UIComponent component = viewRoot.findComponent(path);
        Assert.notNull(component);
        Assert.isInstanceOf(DataTable.class, component);
        DataTable dataTable = (DataTable) component;
        dataTable.setValueExpression("sortBy", null);
        dataTable.setSortColumn(dataTable.getColumns().get(0));
        dataTable.setSortOrder(sortOrder);
    }

    @SuppressWarnings("deprecation")
    public static void resetDataTableSort(String path, Integer columnIndex, String sortOrder) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return;
        }

        UIComponent component = viewRoot.findComponent(path);
        Assert.notNull(component);
        Assert.isInstanceOf(DataTable.class, component);
        DataTable dataTable = (DataTable) component;
        dataTable.setValueExpression("sortBy", null);
        dataTable.setSortColumn(dataTable.getColumns().get(columnIndex));
        dataTable.setSortOrder(sortOrder);
    }

    @SuppressWarnings("deprecation")
    public static void setDataTableSelection(String path, Object selection) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return;
        }
        UIViewRoot viewRoot = fc.getViewRoot();
        if (viewRoot == null) {
            LOG.warn("FacesContext.getViewRoot() is null, is it for unit test?");
            return;
        }

        UIComponent component = viewRoot.findComponent(path);
        Assert.notNull(component);
        Assert.isInstanceOf(DataTable.class, component);
        DataTable dataTable = (DataTable) component;
        dataTable.setSelection(selection);
    }

    public static void setInvalidDataTableSelectMenu(String tablepath) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIData table = (UIData) context.getViewRoot().findComponent(tablepath);
        table.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof HtmlSelectOneMenu) {
                    if (((HtmlSelectOneMenu) target).getValue().toString().equals("0")) {
                        ((HtmlSelectOneMenu) target).setValid(false);
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void setInvalidDataTableCalendar(String tablepath, Date date) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIData table = (UIData) context.getViewRoot().findComponent(tablepath);
        table.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @SuppressWarnings("deprecation")
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof Calendar) {
                    if (date != null && ((Calendar) target).getValue() != null) {
                        if (date.getYear() <= 0) {
                            ((Calendar) target).setValid(false);
                        }
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void setInvaliTabPanelCalendar(String panelPath, Date date) {
        FacesContext context = FacesContext.getCurrentInstance();
        UITabPanel panel = (UITabPanel) context.getViewRoot().findComponent(panelPath);
        panel.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @SuppressWarnings("deprecation")
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof Calendar) {
                    if (((Calendar) target).getValue() == null) {
                        if (ConstantCommon.DATE_OF_RELEASE.equals(target.getId())) {
                            ((Calendar) target).setValid(false);
                        }
                    } else {
                        if (date != null) {
                            if (date.getYear() <= 0) {
                                ((Calendar) target).setValid(false);
                            }
                        }
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void setInvalidTabPanelSelectMenu(String panelPath, String menuId, int index) {
        FacesContext context = FacesContext.getCurrentInstance();
        UITabPanel panel = (UITabPanel) context.getViewRoot().findComponent(panelPath);
        panel.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof HtmlSelectOneMenu) {
                    String value = ((HtmlSelectOneMenu) target).getValue() != null
                            ? ((HtmlSelectOneMenu) target).getValue().toString() : "";
                    if (menuId.equals(target.getId()) && StringUtils.isBlank(value) && index == panel.getIndex()) {
                        ((HtmlSelectOneMenu) target).setValid(false);
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void setInvalidTabPanelInput(String panelPath, String inputId) {
        FacesContext context = FacesContext.getCurrentInstance();
        UITabPanel panel = (UITabPanel) context.getViewRoot().findComponent(panelPath);
        panel.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof InputText) {
                    String value = ((InputText) target).getValue() != null ? ((InputText) target).getValue().toString()
                            : "";
                    if (inputId.equals(target.getId()) && StringUtils.isBlank(value)) {
                        ((InputText) target).setValid(false);
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void setInvalidTabPanelTableFooter(String panelPath, String tableId, String inputId, int index) {
        FacesContext context = FacesContext.getCurrentInstance();
        UITabPanel panel = (UITabPanel) context.getViewRoot().findComponent(panelPath);
        panel.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target instanceof DataTable) {
                    if (tableId.equals(target.getId())) {
                        DataTable table = (DataTable) target;
                        UIComponent footer = table.getFooter();
                        footer.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()),
                                new VisitCallback() {
                                    @Override
                                    public VisitResult visit(VisitContext context, UIComponent footTarget) {
                                        if (footTarget instanceof InputNumber) {
                                            if (inputId.equals(footTarget.getId()) && index == panel.getIndex()) {
                                                ((InputNumber) footTarget).setValid(false);
                                            }
                                        }
                                        return VisitResult.ACCEPT;
                                    }
                                });
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
    }

    public static void displayManFieldMissingDialog(Locale locale) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (locale.equals(ConstantCommon.LOCALE_UK)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE,
                    ConstantCommon.MADATORY_FIELD_MISSING));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE_CHI,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI));
        }
    }

    public static void displayWrongDateFormatDialog(Locale locale) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (locale.equals(ConstantCommon.LOCALE_UK)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE,
                    ConstantCommon.WRONG_DATE_FORMAT));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE_CHI,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI));
        }
    }

    public static void displaySaveSuccessDialog(Locale locale) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (locale.equals(ConstantCommon.LOCALE_UK)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantCommon.MESSAGE, ConstantCommon.SAVE_SUCCESS));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantCommon.MESSAGE,
                    ConstantCommon.SAVE_SUCCESS_CHI));
        }
    }

    public static void displaySaveFailedDialog(Locale locale) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (locale.equals(ConstantCommon.LOCALE_UK)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantCommon.MESSAGE, ConstantCommon.SAVE_FAIL));
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantCommon.MESSAGE, ConstantCommon.SAVE_FAIL_CHI));
        }
    }

    /**
     * public show dialog
     * 
     * @param locale
     * @param eng
     *            English
     * @param chi
     *            Chinese
     */
    public static void displayErrorDialog(Locale locale, String eng, String chi) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (locale.equals(ConstantCommon.LOCALE_UK)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE, eng));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantCommon.MESSAGE_CHI, chi));
        }
    }
}
