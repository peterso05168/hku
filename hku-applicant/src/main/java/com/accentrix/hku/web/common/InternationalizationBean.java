package com.accentrix.hku.web.common;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;

@ManagedBean(name = "language")
@SessionScoped
public class InternationalizationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Locale ENG = ConstantCommon.LOCALE_UK;
    private final static Locale CHI = ConstantCommon.LOCALE_CHINESE;
    public static Locale locale = ENG;
    private String engSeleted = Constants.SELECTED;
    private String chiSeleted;

    public Locale getLocale() {
        return (locale);
    }

    public void setEnglish(ActionEvent event) {
        locale = ENG;
        engSeleted = Constants.SELECTED;
        chiSeleted = "";
        updateViewLocale();
    }

    public void setChinese(ActionEvent event) {
        locale = CHI;
        engSeleted = "";
        chiSeleted = Constants.SELECTED;
        updateViewLocale();
    }

    private void updateViewLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getEngSeleted() {
        return engSeleted;
    }

    public void setEngSeleted(String engSeleted) {
        this.engSeleted = engSeleted;
    }

    public String getChiSeleted() {
        return chiSeleted;
    }

    public void setChiSeleted(String chiSeleted) {
        this.chiSeleted = chiSeleted;
    }
}