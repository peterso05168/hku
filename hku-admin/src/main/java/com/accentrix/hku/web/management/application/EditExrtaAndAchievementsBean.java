package com.accentrix.hku.web.management.application;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.ExpAndAchievementsService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.ExpAndAchievementsVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年5月30日 下午7:14:51 
*/
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditExrtaAndAchievementsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(EditExrtaAndAchievementsBean.class);

    @Autowired
    private ExpAndAchievementsService expAndAchievementsService;
    @Autowired
    private ProgressService progressService;

    private ExpAndAchievementsVo expAndAchievementsVo;
    private List<ExpAndAchievementsVo> leadershipAchievementsVos;
    private List<ExpAndAchievementsVo> commAchievementsVos;
    private List<ExpAndAchievementsVo> gmAchievementsVos;
    private List<ExpAndAchievementsVo> aaosAchievementsVos;
    private List<ExpAndAchievementsVo> othersAchievementsVos;
    private String dateForm;
    private String dateTo;
    private String applicationId;
    private ProgressVo progressVo;
    private String fromFormat;
    private String toFormat;

    public EditExrtaAndAchievementsBean() {
        init();
    }

    public void init() {
        LOG.debug("init");
        leadershipAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        commAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        gmAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        aaosAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        othersAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        retrieveData(applicationId);
        fromFormat = Constants.FROM_WRONG;
        toFormat = Constants.TO_WRONG;
    }

    /**
     * 
     * @param applicationId
     */
    private void retrieveData(String applicationId) {
        expAndAchievementsVo = new ExpAndAchievementsVo();

        leadershipAchievementsVos = expAndAchievementsService.findListByApplicationIdAndCat(applicationId,
                Constants.LEADERSHIP);
        for (ExpAndAchievementsVo expAndAchievementsVo : leadershipAchievementsVos) {
            expDateFormat(expAndAchievementsVo);
        }

        commAchievementsVos = expAndAchievementsService.findListByApplicationIdAndCat(applicationId, Constants.COMM);
        for (ExpAndAchievementsVo expAndAchievementsVo : commAchievementsVos) {
            expDateFormat(expAndAchievementsVo);
        }

        gmAchievementsVos = expAndAchievementsService.findListByApplicationIdAndCat(applicationId, Constants.GM);
        for (ExpAndAchievementsVo expAndAchievementsVo : gmAchievementsVos) {
            expDateFormat(expAndAchievementsVo);
        }

        aaosAchievementsVos = expAndAchievementsService.findListByApplicationIdAndCat(applicationId, Constants.AAOS);
        for (ExpAndAchievementsVo expAndAchievementsVo : aaosAchievementsVos) {
            expDateFormat(expAndAchievementsVo);
        }

        othersAchievementsVos = expAndAchievementsService.findListByApplicationIdAndCat(applicationId,
                Constants.EXTRA_OTHERS);
        for (ExpAndAchievementsVo expAndAchievementsVo : othersAchievementsVos) {
            expDateFormat(expAndAchievementsVo);
        }
    }

    public boolean save() {
        if (!validateExpAndAch()) {
            return false;
        }
        expAndAchievementsVo.setApplicationId(applicationId);
        expAndAchievementsVo.setIsDeleted(false);
        try {
            expAndAchievementsVo = expAndAchievementsService.save(expAndAchievementsVo);
            if (expAndAchievementsVo != null) {
                UIUtil.execute("PF('expDialog').hide()");
                retrieveData(applicationId);
                if (StringUtils.isNotBlank(applicationId)) {
                    progressVo = progressService.findByApplicationId(applicationId);
                    progressVo.setExpAndAchi(true);
                    progressService.save(progressVo);
                }
            }
        } catch (Exception e) {

        }
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    // 验证
    @SuppressWarnings("deprecation")
    public boolean validateExpAndAch() {
        boolean valid = true;
        boolean wrongDateFormat = false;

        if (StringUtils.isEmpty(expAndAchievementsVo.getName())) {
            UIUtil.setInvalid(":mainTab:expDialogForm:name");
            valid = false;
        }
        if (expAndAchievementsVo.getDateFrom() == null) {
            UIUtil.setInvalid(":mainTab:expDialogForm:dateFrom");
            valid = false;
        } else {
            if (expAndAchievementsVo.getDateFrom().getYear() <= 0) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":mainTab:expDialogForm:dateFrom");
            }
        }
        if (expAndAchievementsVo.getDateTo() == null) {
            UIUtil.setInvalid(":mainTab:expDialogForm:dateTo");
            valid = false;
        } else {
            if (expAndAchievementsVo.getDateTo().getYear() <= 0) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":mainTab:expDialogForm:dateTo");
            }
        }

        if (!StringUtils.isNotEmpty(expAndAchievementsVo.getOrganizer())) {
            UIUtil.setInvalid(":mainTab:expDialogForm:organizer");
            valid = false;
        }
        if (!StringUtils.isNotEmpty(expAndAchievementsVo.getRole())) {
            UIUtil.setInvalid(":mainTab:expDialogForm:role");
            valid = false;
        }
        if (!valid && wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            return false;
        } else if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else if (wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            return false;
        } else {
            return true;
        }
    }

    public void edit(String expAndAchiId, String catCd) {
        if (StringUtils.isNotEmpty(expAndAchiId)) {
            expAndAchievementsVo = expAndAchievementsService.get(expAndAchiId);
        } else {
            boolean valid = false;
            switch (catCd) {
            case Constants.LEADERSHIP:
                if (leadershipAchievementsVos.size() < 3) {
                    valid = true;
                }
                break;
            case Constants.COMM:
                if (commAchievementsVos.size() < 3) {
                    valid = true;
                }
                break;
            case Constants.GM:
                if (gmAchievementsVos.size() < 3) {
                    valid = true;
                }
                break;
            case Constants.AAOS:
                if (aaosAchievementsVos.size() < 3) {
                    valid = true;
                }
                break;
            case Constants.EXTRA_OTHERS:
                if (othersAchievementsVos.size() < 3) {
                    valid = true;
                }
                break;
            default:
                break;
            }
            if (valid) {
                expAndAchievementsVo = new ExpAndAchievementsVo();
                expAndAchievementsVo.setActivityCatCd(catCd);
                UIUtil.execute("PF('expDialog').show()");
            } else {
                UIUtil.execute("PF('messageDialog').show()");
            }
        }
    }

    public void delete(String expAndAchiId) {
        expAndAchievementsService.delete(expAndAchiId);
        retrieveData(applicationId);
    }

    public void expDateFormat(ExpAndAchievementsVo achievementsVo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 定义要输出日期字符串的
        achievementsVo.setFrom(sdf.format(achievementsVo.getDateFrom()));
        achievementsVo.setTo(sdf.format(achievementsVo.getDateTo()));
    }

    public ExpAndAchievementsVo getExpAndAchievementsVo() {
        return expAndAchievementsVo;
    }

    public void setExpAndAchievementsVo(ExpAndAchievementsVo expAndAchievementsVo) {
        this.expAndAchievementsVo = expAndAchievementsVo;
    }

    public List<ExpAndAchievementsVo> getLeadershipAchievementsVos() {
        return leadershipAchievementsVos;
    }

    public void setLeadershipAchievementsVos(List<ExpAndAchievementsVo> leadershipAchievementsVos) {
        this.leadershipAchievementsVos = leadershipAchievementsVos;
    }

    public String getDateForm() {
        return dateForm;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public ProgressVo getProgressVo() {
        return progressVo;
    }

    public void setProgressVo(ProgressVo progressVo) {
        this.progressVo = progressVo;
    }

    public String getFromFormat() {
        return fromFormat;
    }

    public void setFromFormat(String fromFormat) {
        this.fromFormat = fromFormat;
    }

    public String getToFormat() {
        return toFormat;
    }

    public void setToFormat(String toFormat) {
        this.toFormat = toFormat;
    }

    public List<ExpAndAchievementsVo> getCommAchievementsVos() {
        return commAchievementsVos;
    }

    public void setCommAchievementsVos(List<ExpAndAchievementsVo> commAchievementsVos) {
        this.commAchievementsVos = commAchievementsVos;
    }

    public List<ExpAndAchievementsVo> getGmAchievementsVos() {
        return gmAchievementsVos;
    }

    public void setGmAchievementsVos(List<ExpAndAchievementsVo> gmAchievementsVos) {
        this.gmAchievementsVos = gmAchievementsVos;
    }

    public List<ExpAndAchievementsVo> getAaosAchievementsVos() {
        return aaosAchievementsVos;
    }

    public void setAaosAchievementsVos(List<ExpAndAchievementsVo> aaosAchievementsVos) {
        this.aaosAchievementsVos = aaosAchievementsVos;
    }

    public List<ExpAndAchievementsVo> getOthersAchievementsVos() {
        return othersAchievementsVos;
    }

    public void setOthersAchievementsVos(List<ExpAndAchievementsVo> othersAchievementsVos) {
        this.othersAchievementsVos = othersAchievementsVos;
    }
}
