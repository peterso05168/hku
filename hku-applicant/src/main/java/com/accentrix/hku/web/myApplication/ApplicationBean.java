package com.accentrix.hku.web.myApplication;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.service.app.OthersService;
import com.accentrix.hku.service.app.PersonalParticularsService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.ReferenceService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.AcadBgVo;
import com.accentrix.hku.vo.app.OthersVo;
import com.accentrix.hku.vo.app.PersonalParticularsVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.ReferenceVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author 作者 lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年2月2日 下午6:52:49
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ApplicationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationBean.class);

    @Autowired
    private PersonalParticularsService personalParticularsService;
    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private AcadBgService acadBgService;
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private FormService formService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private OthersService othersService;
    @Autowired
    private ExeService exeService;

    private PersonalParticularsVo personalParticulars;
    private AccountVo accountVo;
    private ApplicantInformationVo applicantInformationVo;
    private AcadBgVo acadBgVo;
    private ReferenceVo referenceVo;
    private ApplicationVo applicationVo;
    private OthersVo othersVo;
    private List<FormVo> formVos;
    private ProgressVo progressVo;
    private Integer currTabIndex;
    private Integer tabIndex;
    private int i;
    private Date date = null;

    public ApplicationBean() {
        init();
    }

    public void init() {
        LOG.debug("init");
        currTabIndex = 0;
        formVos = formService.findList();
        for (FormVo formVo : formVos) {
            i = 0;
            applicationVo = applicationService.findByAdmFormIdAndAccountId(formVo.getId(), AccountUtils.getLoginId());
            if (applicationVo != null) {
                progressVo = progressService.findByApplicationId(applicationVo.getId());
                if (progressVo != null) {
                    if (progressVo.getPrsnalPart()) {
                        i++;
                    }
                    if (progressVo.getAcadBg()) {
                        i++;
                    }
                    if (progressVo.getOtherQuali()) {
                        i++;
                    }
                    if (progressVo.getChoiceOfCurri()) {
                        i++;
                    }
                    if (progressVo.getExpAndAchi()) {
                        i++;
                    }
                    if (progressVo.getReference()) {
                        i++;
                    }
                    if (progressVo.getOthers()) {
                        i++;
                    }
                    float sum = (float) i / 7 * 100;
                    formVo.setNum(Math.round(sum));
                    if (Constants.STATUS_IN_PROCESS.equals(applicationVo.getStatus())) {
                        formVo.setStatusColor(Constants.RED);
                    } else {
                        formVo.setStatusColor(Constants.GREEN);
                    }
                    formVo.setStatus(applicationVo.getStatus());
                    if (StringUtils.isNotBlank(applicationVo.getApplicationNo())) {
                        formVo.setApplicationNo(" - " + applicationVo.getApplicationNo());
                    }
                }
            } else {
                formVo.setStatus(Constants.STATUS_NEW);
                formVo.setStatusColor(Constants.BLUE);
            }
        }
    }

    public void onTabChange() {
        // 设置当前tab的index
        currTabIndex = tabIndex;
        boolean isActiveRefree = (boolean) JSFUtil.getSessionMap().get("isActiceRefree");
        if (currTabIndex == 0) {
            PersonalParticularsBean personalParticularsBean = JSFUtil.getCurrentInstance("personalParticularsBean",
                    PersonalParticularsBean.class);
            personalParticularsBean.init();
        } else if (currTabIndex == 1) {
            AcadBgBean acadBgBean = JSFUtil.getCurrentInstance("acadBgBean", AcadBgBean.class);
            acadBgBean.init();
        } else if (currTabIndex == 2) {
            OtherQualificationBean otherQualificationBean = JSFUtil.getCurrentInstance("otherQualificationBean",
                    OtherQualificationBean.class);
            otherQualificationBean.init();
        } else if (currTabIndex == 3) {
            ChoiceOfCurriculumBean choiceOfCurriculumBean = JSFUtil.getCurrentInstance("choiceOfCurriculumBean",
                    ChoiceOfCurriculumBean.class);
            choiceOfCurriculumBean.init();
        } else if (currTabIndex == 4) {
            ExpAndAchievementsBean expAndAchievementsBean = JSFUtil.getCurrentInstance("expAndAchievementsBean",
                    ExpAndAchievementsBean.class);
            expAndAchievementsBean.init();
        } else if (currTabIndex == 5 && isActiveRefree) {
            RefereeBean refereeBean = JSFUtil.getCurrentInstance("refereeBean", RefereeBean.class);
            refereeBean.init();
        } else if (currTabIndex == 5 && !isActiveRefree) {
            OthersBean othersBean = JSFUtil.getCurrentInstance("othersBean", OthersBean.class);
            othersBean.init();
        } else if (currTabIndex == 6) {
            OthersBean othersBean = JSFUtil.getCurrentInstance("othersBean", OthersBean.class);
            othersBean.init();
        }
        UIUtil.hide("applicationDialog");
    }

    public void nextPage(String index) {
        currTabIndex = Integer.valueOf(index);
        if (currTabIndex == 0) {
            AcadBgBean acadBgBean = JSFUtil.getCurrentInstance("acadBgBean", AcadBgBean.class);
            acadBgBean.init();
        }
        if (currTabIndex <= 6) {
            currTabIndex++;
        }
    }

    public void prePage(String index) {
        currTabIndex = Integer.valueOf(index);
        if (currTabIndex == 2) {
            AcadBgBean acadBgBean = JSFUtil.getCurrentInstance("acadBgBean", AcadBgBean.class);
            acadBgBean.init();
            acadBgBean.setCurrTabIndex(1);
        }
        if (currTabIndex > 0) {
            currTabIndex--;
        }
    }

    public void dateFormat(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 定义要输出日期字符串的
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void savePerson() {
        accountVo = AccountUtils.getAccountVo();
        applicantInformationVo = applicantInformationService.get(accountVo.getUserInfoId());

        String time = "1900-01-01";
        dateFormat(time);
        personalParticulars = new PersonalParticularsVo();
        personalParticulars.setEmail(applicantInformationVo.getAlternateEmail());
        personalParticulars.setSurname(applicantInformationVo.getSurname());
        personalParticulars.setGivenName(applicantInformationVo.getGivenName());
        personalParticulars.setDateOfBirth(date);
        personalParticulars.setSex(String.valueOf(applicantInformationVo.getSex()));
        personalParticulars.setNationalityCountryId(applicantInformationVo.getNationalityCountryId());
        personalParticulars.setResidenceCountryId(applicantInformationVo.getResidenceCountryId());
        personalParticulars.setMobileTelNoId(applicantInformationVo.getMobileTelId());
        personalParticulars.setHomeTelNoId(applicantInformationVo.getHomeTelId());
        personalParticulars.setReqVisaInd(false);
        personalParticulars = personalParticularsService.save(personalParticulars);
    }

    public void saveAcadBg() {
        String time = "1900-01-01";
        dateFormat(time);
        acadBgVo = new AcadBgVo();
        acadBgVo.setProgrammeTypeCd("");
        acadBgVo.setProgrammeTitle(" ");
        acadBgVo.setDateOfAdmissionToProg(date);
        acadBgVo.setCurrentYrOfStudy(" ");
        acadBgVo.setExpectedDateOfGrad(date);
        acadBgVo.setLatestCumulativeGpa(" ");
        acadBgVo.setMaxGpa(" ");
        acadBgVo.setHighestQualificationCd(" ");
        acadBgVo.setIsNotStuding(false);
        acadBgVo.setIsPartcpNextNjcee(false);
        acadBgVo = acadBgService.save(acadBgVo);
    }

    public void saveReference() {
        referenceVo = new ReferenceVo();
        referenceVo.setCreateBy(" ");
        referenceVo = referenceService.save(referenceVo);
    }

    public void saveOthers() {
        othersVo = new OthersVo();
        othersVo.setDeReg(false);
        othersVo.setDiscontinued(false);
        othersVo = othersService.save(othersVo);
    }

    public void editApplicantApplication(String admFormId) {
        applicationVo = applicationService.findByAdmFormIdAndAccountId(admFormId, AccountUtils.getLoginId());
        FormVo formVo = formService.get(admFormId);
        ExeVo exeVo = exeService.get(formVo.getAdmExeId());
        if (applicationVo == null) {
            savePerson();
            saveAcadBg();
            saveReference();
            saveOthers();

            applicationVo = new ApplicationVo();
            applicationVo.setPersonId(personalParticulars.getId());
            applicationVo.setAcadBgId(acadBgVo.getId());
            applicationVo.setReferenceId(referenceVo.getId());
            applicationVo.setOthersId(othersVo.getId());
            applicationVo.setApplicantAccountId(accountVo.getId());
            applicationVo.setAdmFormId(admFormId);
            applicationVo.setApplicationNo("");
            applicationVo.setStatus(Constants.STATUS_IN_PROCESS);
            applicationVo.setEngReq(false);
            applicationVo = applicationService.save(applicationVo);
            if (applicationVo != null) {
                progressVo = new ProgressVo();
                progressVo.setApplicationId(applicationVo.getId());
                progressVo.setAcadBg(false);
                progressVo.setPrsnalPart(false);
                progressVo.setOtherQuali(false);
                progressVo.setChoiceOfCurri(false);
                progressVo.setExpAndAchi(false);
                progressVo.setReference(false);
                progressVo.setOthers(false);
                progressVo = progressService.save(progressVo);
            }
        }
        JSFUtil.getSessionMap().put("admFormId", applicationVo.getAdmFormId());
        JSFUtil.getSessionMap().put("applicationId", applicationVo.getId());
        JSFUtil.getSessionMap().put("housingMgmtId", applicationVo.getAcadQualHousingMgmtId());
        JSFUtil.getSessionMap().put("appEndDate", exeVo.getApplicationEndDate());
        JSFUtil.getSessionMap().put("choiceEndDate", exeVo.getProgrammeChoiceEndDate());
        JSFUtil.getSessionMap().put("admissionYear", exeVo.getAdmissionYear());
        JSFUtil.getSessionMap().put("nursingId", applicationVo.getAcadQualNursingId());
        UIUtil.execute("window.location = 'application-form.xhtml'");
    }

    public void redirectToSupportingDocument(String admFormId) {
        applicationVo = applicationService.findByAdmFormIdAndAccountId(admFormId, AccountUtils.getLoginId());
        JSFUtil.getSessionMap().put("applicationVo", applicationVo);
    }

    public PersonalParticularsVo getPersonalParticulars() {
        return personalParticulars;
    }

    public void setPersonalParticulars(PersonalParticularsVo personalParticulars) {
        this.personalParticulars = personalParticulars;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    public AcadBgVo getAcadBgVo() {
        return acadBgVo;
    }

    public void setAcadBgVo(AcadBgVo acadBgVo) {
        this.acadBgVo = acadBgVo;
    }

    public ReferenceVo getReferenceVo() {
        return referenceVo;
    }

    public void setReferenceVo(ReferenceVo referenceVo) {
        this.referenceVo = referenceVo;
    }

    public ApplicationVo getApplicationVo() {
        return applicationVo;
    }

    public void setApplicationVo(ApplicationVo applicationVo) {
        this.applicationVo = applicationVo;
    }

    public Integer getCurrTabIndex() {
        return currTabIndex;
    }

    public void setCurrTabIndex(Integer currTabIndex) {
        this.currTabIndex = currTabIndex;
    }

    public List<FormVo> getFormVos() {
        return formVos;
    }

    public void setFormVos(List<FormVo> formVos) {
        this.formVos = formVos;
    }

    public OthersVo getOthersVo() {
        return othersVo;
    }

    public void setOthersVo(OthersVo othersVo) {
        this.othersVo = othersVo;
    }

    public ProgressVo getProgressVo() {
        return progressVo;
    }

    public void setProgressVo(ProgressVo progressVo) {
        this.progressVo = progressVo;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Integer getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(Integer tabIndex) {
        this.tabIndex = tabIndex;
    }
}
