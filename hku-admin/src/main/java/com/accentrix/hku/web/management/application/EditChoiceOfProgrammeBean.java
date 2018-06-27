package com.accentrix.hku.web.management.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.service.app.AcadQualHousingMgmtPqService;
import com.accentrix.hku.service.app.AcadQualHousingMgmtRweService;
import com.accentrix.hku.service.app.AcadQualHousingMgmtService;
import com.accentrix.hku.service.app.AcadQualNursingExpService;
import com.accentrix.hku.service.app.AcadQualNursingNrService;
import com.accentrix.hku.service.app.AcadQualNursingPrqService;
import com.accentrix.hku.service.app.AcadQualNursingService;
import com.accentrix.hku.service.app.AcadQualOthersService;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.app.PersonalParticularsService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.SpecialSchemeService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.scholar.ScholarService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.app.AcadBgVo;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtPqVo;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtRweVo;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtVo;
import com.accentrix.hku.vo.app.AcadQualNursingExpVo;
import com.accentrix.hku.vo.app.AcadQualNursingNrVo;
import com.accentrix.hku.vo.app.AcadQualNursingPrqVo;
import com.accentrix.hku.vo.app.AcadQualNursingVo;
import com.accentrix.hku.vo.app.AcadQualOthersVo;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.app.PersonalParticularsVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.SpecialSchemeVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.scholar.ScholarVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月30日 下午5:25:55
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditChoiceOfProgrammeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(EditChoiceOfProgrammeBean.class);

    private static final String DOWNLOAD_PATH_SERVER = "/var/downloadables/scholarships/";

    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private SpecialSchemeService specialSchemeService;
    @Autowired
    private AcadQualHousingMgmtService housingMgmtService;
    @Autowired
    private AcadQualOthersService acadQualOthersService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private FormProgService formProgService;
    @Autowired
    private PersonalParticularsService personalParticularsService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ScholarService scholarService;
    @Autowired
    private AcadBgService acadBgService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private AcadQualHousingMgmtRweService acadQualHousingMgmtRweService;
    @Autowired
    private AcadQualHousingMgmtPqService acadQualHousingMgmtPqService;
    @Autowired
    private AcadQualNursingService acadQualNursingService;
    @Autowired
    private AcadQualNursingNrService acadQualNursingNrService;
    @Autowired
    private AcadQualNursingPrqService acadQualNursingPrqService;
    @Autowired
    private AcadQualNursingExpService acadQualNursingExpService;

    private ProgrammeChoiceVo firstProgChoice;
    private String applicationId;
    private String housingMgmtId;
    private String admFormId;
    private List<ProgrammeChoiceVo> othersProgChoices;
    private SpecialSchemeVo sportScholarship;
    private SpecialSchemeVo musicSpecialist;
    private SpecialSchemeVo specialist;
    private AcadQualHousingMgmtVo acadQualHousingMgmt;
    private List<AcadQualOthersVo> acadQualOthers;
    private List<FormProgVo> formProgs;
    private String firstChoiceProgId;
    private List<String> otherChoiceProgIds;
    private Date choiceEndDate;
    private Date appEndDate;
    private boolean isChoiceEndDate;
    private PersonalParticularsVo personalParticularsVo;
    private CountryVo countryVo;
    private ScholarVo heForSheScholarVo;
    private AcadBgVo acadBgVo;
    private InstitutionVo institutionVo;
    private ScholarVo uwcScholarVo;
    private ScholarVo aflScholarVo;
    private ApplicationVo applicationVo;
    private List<AcadQualHousingMgmtPqVo> acadQualHousingMgmtPqVos;
    private List<AcadQualHousingMgmtRweVo> acadQualHousingMgmtRweVos;
    private String nursingId;
    private AcadQualNursingVo acadQualNursingVo;
    private List<AcadQualNursingNrVo> acadQualNursingNrVos;
    private List<AcadQualNursingPrqVo> acadQualNursingPrqVos;
    private List<AcadQualNursingExpVo> acadQualNursingExpVos;
    private List<AcadQualNursingExpVo> acadQualNursingOtherExpVos;

    private StreamedContent downloadFile;

    public EditChoiceOfProgrammeBean() {
        init();
    }

    public void init() {
        LOG.debug("init ...");
        resetData();

        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        housingMgmtId = (String) JSFUtil.getSessionMap().get("housingMgmtId");
        admFormId = (String) JSFUtil.getSessionMap().get("admFormId");
        choiceEndDate = (Date) JSFUtil.getSessionMap().get("choiceEndDate");
        appEndDate = (Date) JSFUtil.getSessionMap().get("appEndDate");
        nursingId = (String) JSFUtil.getSessionMap().get("nursingId");
        retrieveData(applicationId);
    }

    private void resetData() {
        firstProgChoice = new ProgrammeChoiceVo();
        othersProgChoices = new ArrayList<ProgrammeChoiceVo>();
        acadQualHousingMgmt = new AcadQualHousingMgmtVo();
        acadQualOthers = new ArrayList<AcadQualOthersVo>();
        formProgs = new ArrayList<FormProgVo>();
        otherChoiceProgIds = new ArrayList<String>();
        sportScholarship = new SpecialSchemeVo();
        musicSpecialist = new SpecialSchemeVo();
        specialist = new SpecialSchemeVo();
        personalParticularsVo = new PersonalParticularsVo();
        countryVo = new CountryVo();
        heForSheScholarVo = new ScholarVo();
        acadBgVo = new AcadBgVo();
        uwcScholarVo = new ScholarVo();
        aflScholarVo = new ScholarVo();
        applicationVo = new ApplicationVo();
        acadQualHousingMgmtRweVos = new ArrayList<AcadQualHousingMgmtRweVo>();
        acadQualHousingMgmtPqVos = new ArrayList<AcadQualHousingMgmtPqVo>();
        acadQualNursingVo = new AcadQualNursingVo();
        acadQualNursingNrVos = new ArrayList<AcadQualNursingNrVo>();
        acadQualNursingPrqVos = new ArrayList<AcadQualNursingPrqVo>();
        acadQualNursingExpVos = new ArrayList<AcadQualNursingExpVo>();
        acadQualNursingOtherExpVos = new ArrayList<AcadQualNursingExpVo>();
    }

    /**
     * Retrieve Data
     * 
     * @param applicationId
     */
    private void retrieveData(String applicationId) {
        if (StringUtils.isNotBlank(applicationId)) {
            applicationVo = applicationService.get(applicationId);
            firstProgChoice = programmeChoiceService.getFirstChoiceByApplicationId(applicationId);
            othersProgChoices = programmeChoiceService.getOtherChoiceByApplicationId(applicationId);
            sportScholarship = specialSchemeService.getByCodeAndApplicationId(Constants.SPORT, applicationId);
            musicSpecialist = specialSchemeService.getByCodeAndApplicationId(Constants.MUSIC, applicationId);
        }

        if (StringUtils.isNotBlank(applicationVo.getPersonId())) {
            personalParticularsVo = personalParticularsService.get(applicationVo.getPersonId());
        }
        if (StringUtils.isNotBlank(applicationVo.getAcadBgId())) {
            acadBgVo = acadBgService.get(applicationVo.getAcadBgId());
        }
        if (StringUtils.isNotBlank(personalParticularsVo.getNationalityCountryId())) {
            countryVo = countryService.get(personalParticularsVo.getNationalityCountryId());
        }
        if (othersProgChoices.size() < 4) {
            for (int i = othersProgChoices.size(); i < 4; i++) {
                othersProgChoices.add(new ProgrammeChoiceVo());
            }
        }

        if (StringUtils.isNotBlank(sportScholarship.getId())) {
            sportScholarship.setIsActiveSport(true);
        }
        if (StringUtils.isNotBlank(musicSpecialist.getId())) {
            musicSpecialist.setIsActiveMusic(true);
        }
        retrieveQualifications(Constants.BACHELOR_OF_ARTS, "");
        if (retrieveQualifications(Constants.HOUSING_MANAGEMENT, "")) {
            getHousingMgmtQualOthers();
        }
        if (retrieveQualifications(Constants.BACHELOR_OF_NURSING, Constants.PART_TIME)) {
            getNursingOthers();
        }

        formProgs = formProgService.getByAdmFormId(admFormId);
        setFormProgDisabled();
        firstChoiceProgId = firstProgChoice.getAdmFormProgId();
        for (ProgrammeChoiceVo programmeChoiceVo : othersProgChoices) {
            otherChoiceProgIds.add(programmeChoiceVo.getAdmFormProgId());
        }
        if (new Date().compareTo(choiceEndDate) > 0) {
            isChoiceEndDate = true;
        }

        if (Constants.HE_FOR_SHE_COUNTRYS.contains(countryVo.getDescription())) {
            countryVo.setIsDisplayHeForSheScholar(true);
            heForSheScholarVo = scholarService.getByName(Constants.HE_FOR_SHE);
            if (StringUtils.isNotBlank(heForSheScholarVo.getId())) {
                if (heForSheScholarVo.getNotAppEndDate()) {
                    if (appEndDate.compareTo(new Date()) > 0) {
                        heForSheScholarVo.setIsDisplayScholar(true);
                        countryVo.setIsActiveScholar(true);
                    }
                } else {
                    if ((heForSheScholarVo.getScholarStartDate().compareTo(new Date()) < 0)
                            && (heForSheScholarVo.getScholarEndDate().compareTo(new Date()) > 0)) {
                        heForSheScholarVo.setIsDisplayScholar(true);
                        countryVo.setIsActiveScholar(true);
                    }
                }
            }
        }

        if (StringUtils.isNotBlank(acadBgVo.getInstitutionId())) {
            institutionVo = institutionService.get(acadBgVo.getInstitutionId());
            if (Constants.UWC_INSTITUTION_CODES.contains(institutionVo.getCode())) {
                institutionVo.setIsDisplayScholar(true);
                uwcScholarVo = scholarService.getByName(Constants.UWC);
                if (StringUtils.isNotBlank(uwcScholarVo.getId()) && personalParticularsVo.getReqVisaInd()) {
                    if (uwcScholarVo.getNotAppEndDate()) {
                        if (appEndDate.compareTo(new Date()) > 0) {
                            uwcScholarVo.setIsDisplayScholar(true);
                            countryVo.setIsActiveScholar(true);
                        }
                    } else {
                        if ((uwcScholarVo.getScholarStartDate().compareTo(new Date()) < 0)
                                && (uwcScholarVo.getScholarEndDate().compareTo(new Date()) > 0)) {
                            uwcScholarVo.setIsDisplayScholar(true);
                            countryVo.setIsActiveScholar(true);
                        }
                    }
                }
            }
        }

        if (Constants.AFL_COUNTRYS.contains(countryVo.getDescription())) {
            countryVo.setIsDisplayAflScholar(true);
            aflScholarVo = scholarService.getByName(Constants.AFL);
            if (StringUtils.isNotBlank(aflScholarVo.getId())) {
                if (aflScholarVo.getNotAppEndDate()) {
                    if (appEndDate.compareTo(new Date()) > 0) {
                        aflScholarVo.setIsDisplayScholar(true);
                        countryVo.setIsActiveScholar(true);
                    }
                } else {
                    if ((aflScholarVo.getScholarStartDate().compareTo(new Date()) < 0)
                            && (aflScholarVo.getScholarEndDate().compareTo(new Date()) > 0)) {
                        aflScholarVo.setIsDisplayScholar(true);
                        countryVo.setIsActiveScholar(true);
                    }
                }
            }
        }

        if (Constants.HKSAR_GSFT_COUNTRYS.contains(countryVo.getDescription())) {
            countryVo.setIsDisplayHksarGsftScholar(true);
            countryVo.setIsActiveScholar(true);
        }

        if (Constants.NIGERIA.equals(countryVo.getDescription()) && personalParticularsVo.getReqVisaInd()) {
            countryVo.setIsDisplayNigeriaScholar(true);
            countryVo.setIsActiveScholar(true);
        }

        if (Constants.VIET_NAM.equals(countryVo.getDescription())) {
            countryVo.setIsDisplayVtpScholar(true);
            countryVo.setIsActiveScholar(true);
        }

        if (Constants.HKSAR_GSFT_BR_COUNTRYS.contains(countryVo.getDescription())) {
            countryVo.setIsDisplayHksarBrGsftScholar(true);
            countryVo.setIsActiveScholar(true);
        }
    }

    public boolean save() {
        if (!validateContent()) {
            firstProgChoice.setAdmFormProgId(firstChoiceProgId);
            return false;
        }

        try {
            if (StringUtils.isNotBlank(firstChoiceProgId) && !"0".equals(firstChoiceProgId)) {
                firstProgChoice.setAdmFormProgId(firstChoiceProgId);
                firstProgChoice.setApplicationId(applicationId);
                firstProgChoice.setFirstChoice(true);
                if (StringUtils.isBlank(firstProgChoice.getId())) {
                    firstProgChoice.setIsWithdrawn(false);
                    firstProgChoice.setIsWithdrawnApproved(false);
                    firstProgChoice.setProgReq(false);
                    firstProgChoice.setSelectForInterview(false);
                    firstProgChoice.setOtherInterview("N");
                    firstProgChoice.setOfferStatusCd(Constants.CHOICE_SUBMITTED);
                }
                firstProgChoice = programmeChoiceService.save(firstProgChoice);
            }

            for (ProgrammeChoiceVo othersProgChoice : othersProgChoices) {
                if (StringUtils.isNotBlank(othersProgChoice.getAdmFormProgId())
                        && !"0".equals(othersProgChoice.getAdmFormProgId())) {
                    // update choice
                    othersProgChoice.setApplicationId(applicationId);
                    othersProgChoice.setFirstChoice(false);
                    if (StringUtils.isBlank(othersProgChoice.getId())) {
                        othersProgChoice.setIsWithdrawn(false);
                        othersProgChoice.setIsWithdrawnApproved(false);
                        othersProgChoice.setProgReq(false);
                        othersProgChoice.setSelectForInterview(false);
                        othersProgChoice.setOtherInterview("N");
                        othersProgChoice.setOfferStatusCd(Constants.CHOICE_SUBMITTED);
                    }
                    othersProgChoice = programmeChoiceService.save(othersProgChoice);
                } else if ((StringUtils.isBlank(othersProgChoice.getAdmFormProgId())
                        || "0".equals(othersProgChoice.getAdmFormProgId()))
                        && StringUtils.isNotBlank(othersProgChoice.getId())) {
                    // delete choice
                    programmeChoiceService.delete(othersProgChoice.getId());
                }
            }

            if (sportScholarship.getIsActiveSport()) {
                sportScholarship.setSpecialSchemeCd(Constants.SPORT);
                sportScholarship.setApplicationId(applicationId);
                if (sportScholarship.getSptLevels().size() > 0) {
                    sportScholarship.setSptLevel("");
                    for (String sptLevel : sportScholarship.getSptLevels()) {
                        sportScholarship.setSptLevel(sptLevel + "," + sportScholarship.getSptLevel());
                    }
                }
                specialSchemeService.save(sportScholarship);
            } else {
                if (StringUtils.isNotBlank(sportScholarship.getId())) {
                    specialSchemeService.delete(sportScholarship.getId());
                }
            }

            if (musicSpecialist.getIsActiveMusic()) {
                musicSpecialist.setSpecialSchemeCd(Constants.MUSIC);
                musicSpecialist.setApplicationId(applicationId);
                specialSchemeService.save(musicSpecialist);
            } else {
                if (StringUtils.isNotBlank(musicSpecialist.getId())) {
                    specialSchemeService.delete(musicSpecialist.getId());
                }
            }

            if (acadQualHousingMgmt.getIsSelectHousingMgmt()) {
                acadQualHousingMgmt = housingMgmtService.save(acadQualHousingMgmt);
                JSFUtil.getSessionMap().put("housingMgmtId", acadQualHousingMgmt.getId());
                applicationVo.setAcadQualHousingMgmtId(acadQualHousingMgmt.getId());
                for (AcadQualOthersVo acadQualOthersVo : acadQualOthers) {
                    if (validateHousingMgmtOthers(acadQualOthersVo)) {
                        acadQualOthersVo.setAcadQualHousingMgmtId(acadQualHousingMgmt.getId());
                        acadQualOthersService.save(acadQualOthersVo);
                    }
                }
                for (AcadQualHousingMgmtPqVo acadQualHousingMgmtPqVo : acadQualHousingMgmtPqVos) {
                    if (validateHousingMgmtPq(acadQualHousingMgmtPqVo)) {
                        acadQualHousingMgmtPqVo.setAppAcadQualHousingMgmtId(acadQualHousingMgmt.getId());
                        acadQualHousingMgmtPqService.save(acadQualHousingMgmtPqVo);
                    }
                }
                for (AcadQualHousingMgmtRweVo acadQualHousingMgmtRweVo : acadQualHousingMgmtRweVos) {
                    if (validateHousingMgmtRwe(acadQualHousingMgmtRweVo)) {
                        acadQualHousingMgmtRweVo.setAppAcadQualHousingMgmtId(acadQualHousingMgmt.getId());
                        acadQualHousingMgmtRweService.save(acadQualHousingMgmtRweVo);
                    }
                }
            }

            if (acadQualNursingVo.getIsSelectNursing()) {
                acadQualNursingVo = acadQualNursingService.save(acadQualNursingVo);
                JSFUtil.getSessionMap().put("nursingId", acadQualNursingVo.getId());
                applicationVo.setAcadQualNursingId(acadQualNursingVo.getId());
                for (AcadQualNursingNrVo acadQualNursingNrVo : acadQualNursingNrVos) {
                    if (validateNursingNr(acadQualNursingNrVo)) {
                        acadQualNursingNrVo.setAppAcadQualNursingId(acadQualNursingVo.getId());
                        acadQualNursingNrService.save(acadQualNursingNrVo);
                    }
                }
                for (AcadQualNursingPrqVo acadQualNursingPrqVo : acadQualNursingPrqVos) {
                    if (validateNursingPrq(acadQualNursingPrqVo)) {
                        acadQualNursingPrqVo.setAppAcadQualNursingId(acadQualNursingVo.getId());
                        acadQualNursingPrqService.save(acadQualNursingPrqVo);
                    }
                }
                for (AcadQualNursingExpVo acadQualNursingExpVo : acadQualNursingExpVos) {
                    if (validateNursingExp(acadQualNursingExpVo)) {
                        acadQualNursingExpVo.setAppAcadQualNursingId(acadQualNursingVo.getId());
                        acadQualNursingExpVo.setExpType(Constants.N);
                        acadQualNursingExpService.save(acadQualNursingExpVo);
                    }
                }
                for (AcadQualNursingExpVo acadQualNursingExpVo : acadQualNursingOtherExpVos) {
                    if (validateNursingExp(acadQualNursingExpVo)) {
                        acadQualNursingExpVo.setAppAcadQualNursingId(acadQualNursingVo.getId());
                        acadQualNursingExpVo.setExpType(Constants.O);
                        acadQualNursingExpService.save(acadQualNursingExpVo);
                    }
                }
            }

            if (firstProgChoice != null) {
                if (StringUtils.isNotBlank(applicationId)) {
                    ProgressVo progress = progressService.findByApplicationId(applicationId);
                    progress.setChoiceOfCurri(true);
                    progressService.save(progress);
                }
            }
            applicationService.save(applicationVo);
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

    public void loadFaculty(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String formProgId = menu.getValue().toString();
        if (!"0".equals(formProgId)) {
            FormProgVo formProg = formProgService.get(formProgId);
            if (formProg.getProgrammeTitle().contains(Constants.HOUSING_MANAGEMENT)
                    && !acadQualHousingMgmt.getIsSelectHousingMgmt()) {
                getHousingMgmtQualOthers();
                UIUtil.update("mainTab:formChoice:housingMgmtGroup", false);
            }
            if (formProg.getProgrammeTitle().contains(Constants.BACHELOR_OF_ARTS)
                    && !musicSpecialist.getIsDisplayBachelorOfArts()) {
                musicSpecialist.setIsDisplayBachelorOfArts(true);
                UIUtil.update("mainTab:formChoice:musicSpecialistGroup", false);
            }
            if (formProg.getProgrammeTitle().contains(Constants.BACHELOR_OF_NURSING)
                    && !acadQualNursingVo.getIsSelectNursing()) {
                getNursingOthers();
                UIUtil.update("mainTab:formChoice:nursingGroup", false);
            }
        }
        setFormProgDisabled();
        firstChoiceProgId = firstProgChoice.getAdmFormProgId();
        otherChoiceProgIds = new ArrayList<String>();
        for (ProgrammeChoiceVo othersProgChoice : othersProgChoices) {
            otherChoiceProgIds.add(othersProgChoice.getAdmFormProgId());
        }
        if (!retrieveQualifications(Constants.HOUSING_MANAGEMENT, "")) {
            acadQualHousingMgmt.setIsSelectHousingMgmt(false);
            UIUtil.update("mainTab:formChoice:housingMgmtGroup", false);
        }
        if (!retrieveQualifications(Constants.BACHELOR_OF_NURSING, Constants.PART_TIME)) {
            acadQualNursingVo.setIsSelectNursing(false);
            UIUtil.update("mainTab:formChoice:nursingGroup", false);
        }
        if (!retrieveQualifications(Constants.BACHELOR_OF_ARTS, "")) {
            musicSpecialist.setIsDisplayBachelorOfArts(false);
            UIUtil.update("mainTab:formChoice:musicSpecialistGroup", false);
        }
    }

    public void setFormProgDisabled() {
        for (FormProgVo formProgVo : formProgs) {
            formProgVo.setProgDisabled(false);
        }
        if (StringUtils.isNotBlank(acadBgVo.getHighestQualificationCd())) {
            if (!Constants.HIGHEST_QUALIFICATION_CD.contains(acadBgVo.getHighestQualificationCd())) {
                for (FormProgVo formProgVo : formProgs) {
                    String firstCode = formProgVo.getProgrammeCode().substring(0, 1);
                    if ("A".equals(firstCode)) {
                        formProgVo.setProgDisabled(true);
                    }
                }
            }
        } else {
            for (FormProgVo formProgVo : formProgs) {
                String firstCode = formProgVo.getProgrammeCode().substring(0, 1);
                if ("A".equals(firstCode)) {
                    formProgVo.setProgDisabled(true);
                }
            }
        }
        for (FormProgVo formProgVo : formProgs) {
            if (StringUtils.isNotBlank(firstProgChoice.getAdmFormProgId())) {
                if (firstProgChoice.getAdmFormProgId().equals(formProgVo.getId())) {
                    formProgVo.setProgDisabled(true);
                    break;
                }
            }
        }
        for (ProgrammeChoiceVo programmeChoiceVo : othersProgChoices) {
            if (StringUtils.isNotBlank(programmeChoiceVo.getAdmFormProgId())) {
                for (FormProgVo formProgVo : formProgs) {
                    if (programmeChoiceVo.getAdmFormProgId().equals(formProgVo.getId())) {
                        formProgVo.setProgDisabled(true);
                        break;
                    }
                }
            }
        }
    }

    public boolean retrieveQualifications(String programmeTitle, String partTime) {
        if (StringUtils.isNotEmpty(firstProgChoice.getAdmFormProgId())
                && !"0".equals(firstProgChoice.getAdmFormProgId())) {
            FormProgVo formProg = formProgService.get(firstProgChoice.getAdmFormProgId());
            if (formProg.getProgrammeTitle().contains(programmeTitle)
                    && formProg.getProgrammeTitle().contains(partTime)) {
                if (Constants.BACHELOR_OF_ARTS.equals(programmeTitle)) {
                    musicSpecialist.setIsDisplayBachelorOfArts(true);
                }
                return true;
            }
        }

        for (ProgrammeChoiceVo othersProgChoice : othersProgChoices) {
            if (StringUtils.isNotEmpty(othersProgChoice.getAdmFormProgId())
                    && !"0".equals(othersProgChoice.getAdmFormProgId())) {
                FormProgVo formProg = formProgService.get(othersProgChoice.getAdmFormProgId());
                if (formProg.getProgrammeTitle().contains(programmeTitle)
                        && formProg.getProgrammeTitle().contains(partTime)) {
                    if (Constants.BACHELOR_OF_ARTS.equals(programmeTitle)) {
                        musicSpecialist.setIsDisplayBachelorOfArts(true);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void getHousingMgmtQualOthers() {
        if (StringUtils.isNotBlank(housingMgmtId)) {
            acadQualHousingMgmt = housingMgmtService.get(housingMgmtId);
            acadQualOthers = acadQualOthersService.getByAcadQualHousingMgmtId(housingMgmtId);
            acadQualHousingMgmtPqVos = acadQualHousingMgmtPqService.getByAppAcadQualHousingMgmtId(housingMgmtId);
            acadQualHousingMgmtRweVos = acadQualHousingMgmtRweService.getByAppAcadQualHousingMgmtId(housingMgmtId);
        }
        if (acadQualOthers.size() < 2) {
            for (int i = acadQualOthers.size(); i < 2; i++) {
                acadQualOthers.add(new AcadQualOthersVo());
            }
        }
        if (acadQualHousingMgmtPqVos.size() < 3) {
            for (int i = acadQualHousingMgmtPqVos.size(); i < 3; i++) {
                acadQualHousingMgmtPqVos.add(new AcadQualHousingMgmtPqVo());
            }
        }
        if (acadQualHousingMgmtRweVos.size() < 3) {
            for (int i = acadQualHousingMgmtRweVos.size(); i < 3; i++) {
                acadQualHousingMgmtRweVos.add(new AcadQualHousingMgmtRweVo());
            }
        }
        acadQualHousingMgmt.setIsSelectHousingMgmt(true);
    }

    public void getNursingOthers() {
        if (StringUtils.isNotBlank(nursingId)) {
            acadQualNursingVo = acadQualNursingService.get(nursingId);
            acadQualNursingNrVos = acadQualNursingNrService.getByAppAcadQualNursingId(nursingId);
            acadQualNursingPrqVos = acadQualNursingPrqService.getByAppAcadQualNursingId(nursingId);
            acadQualNursingExpVos = acadQualNursingExpService.getByAppAcadQualNursingIdAndType(nursingId, Constants.N);
            acadQualNursingOtherExpVos = acadQualNursingExpService.getByAppAcadQualNursingIdAndType(nursingId,
                    Constants.O);
        }
        if (acadQualNursingNrVos.size() < 3) {
            for (int i = acadQualNursingNrVos.size(); i < 3; i++) {
                acadQualNursingNrVos.add(new AcadQualNursingNrVo());
            }
        }
        if (acadQualNursingPrqVos.size() < 3) {
            for (int i = acadQualNursingPrqVos.size(); i < 3; i++) {
                acadQualNursingPrqVos.add(new AcadQualNursingPrqVo());
            }
        }
        if (acadQualNursingExpVos.size() < 3) {
            for (int i = acadQualNursingExpVos.size(); i < 3; i++) {
                acadQualNursingExpVos.add(new AcadQualNursingExpVo());
            }
        }
        if (acadQualNursingOtherExpVos.size() < 3) {
            for (int i = acadQualNursingOtherExpVos.size(); i < 3; i++) {
                acadQualNursingOtherExpVos.add(new AcadQualNursingExpVo());
            }
        }
        acadQualNursingVo.setIsSelectNursing(true);
    }

    public boolean validateHousingMgmtOthers(AcadQualOthersVo acadQualOthersVo) {
        if (StringUtils.isEmpty(acadQualOthersVo.getDegreeTitle())
                && StringUtils.isEmpty(acadQualOthersVo.getCourseDuration())
                && StringUtils.isEmpty(acadQualOthersVo.getMajorSubject())
                && StringUtils.isEmpty(acadQualOthersVo.getHonours())
                && StringUtils.isEmpty(acadQualOthersVo.getAwardInstitution())
                && acadQualOthersVo.getOtherAwardDate() == null) {
            return false;
        }
        return true;
    }

    public boolean validateHousingMgmtPq(AcadQualHousingMgmtPqVo acadQualHousingMgmtPqVo) {
        if (StringUtils.isBlank(acadQualHousingMgmtPqVo.getTypeOfMem())
                && StringUtils.isBlank(acadQualHousingMgmtPqVo.getAbbre())
                && StringUtils.isBlank(acadQualHousingMgmtPqVo.getAwardInstitution())
                && acadQualHousingMgmtPqVo.getDateAward() == null) {
            return false;
        }
        return true;
    }

    public boolean validateHousingMgmtRwe(AcadQualHousingMgmtRweVo acadQualHousingMgmtRweVo) {
        if (StringUtils.isBlank(acadQualHousingMgmtRweVo.getAppointment())
                && acadQualHousingMgmtRweVo.getDateFrom() == null && acadQualHousingMgmtRweVo.getDateTo() == null
                && StringUtils.isBlank(acadQualHousingMgmtRweVo.getNameOfOrganization())
                && StringUtils.isBlank(acadQualHousingMgmtRweVo.getNatureOfDuties())) {
            return false;
        }
        return true;
    }

    public boolean validateNursingNr(AcadQualNursingNrVo acadQualNursingNrVo) {
        if (StringUtils.isBlank(acadQualNursingNrVo.getNursingSchool()) && acadQualNursingNrVo.getDateFrom() == null
                && acadQualNursingNrVo.getDateTo() == null && acadQualNursingNrVo.getDateOfRegAndIa() == null
                && StringUtils.isBlank(acadQualNursingNrVo.getRegStatus())) {
            return false;
        }
        return true;
    }

    public boolean validateNursingPrq(AcadQualNursingPrqVo acadQualNursingPrqVo) {
        if (StringUtils.isBlank(acadQualNursingPrqVo.getTypeOfMem()) && acadQualNursingPrqVo.getDateFrom() == null
                && acadQualNursingPrqVo.getDateTo() == null && StringUtils.isBlank(acadQualNursingPrqVo.getAbbre())
                && acadQualNursingPrqVo.getDateOfAward() == null
                && StringUtils.isBlank(acadQualNursingPrqVo.getAppAcadQualNursingId())) {
            return false;
        }
        return true;
    }

    public boolean validateNursingExp(AcadQualNursingExpVo acadQualNursingExpVo) {
        if (acadQualNursingExpVo.getDateFrom() == null && acadQualNursingExpVo.getDateTo() == null
                && StringUtils.isBlank(acadQualNursingExpVo.getMode())
                && StringUtils.isBlank(acadQualNursingExpVo.getNameOfInstitute())
                && StringUtils.isBlank(acadQualNursingExpVo.getPositionHeld())
                && StringUtils.isBlank(acadQualNursingExpVo.getAowNod())) {
            return false;
        }
        return true;
    }

    // choice withdraw
    public void withDraw(String id) {
        firstProgChoice.setAdmFormProgId(firstChoiceProgId);
        for (int i = 0; i < othersProgChoices.size(); i++) {
            ProgrammeChoiceVo programmeChoiceVo = othersProgChoices.get(i);
            programmeChoiceVo.setAdmFormProgId(otherChoiceProgIds.get(i));
        }
        if (StringUtils.isNotBlank(id)) {
            ProgrammeChoiceVo programmeChoice = programmeChoiceService.get(id);
            if (appEndDate.getTime() > new Date().getTime()) {
                programmeChoice.setIsWithdrawn(true);
                programmeChoice.setIsWithdrawnApproved(true);
                programmeChoiceService.save(programmeChoice);
                if (!programmeChoice.getFirstChoice()) {
                    resetOtherChoice(programmeChoice.getId());
                } else {
                    if (StringUtils.isNotBlank(applicationId)) {
                        firstProgChoice = programmeChoiceService.getFirstChoiceByApplicationId(applicationId);
                    } else {
                        firstProgChoice = new ProgrammeChoiceVo();
                    }
                }
            } else {
                if (!programmeChoice.getFirstChoice()) {
                    for (int i = 0; i < othersProgChoices.size(); i++) {
                        ProgrammeChoiceVo othersProgChoice = othersProgChoices.get(i);
                        if (id.equals(othersProgChoice.getId())) {
                            programmeChoice.setAdmFormProgId(othersProgChoice.getAdmFormProgId());
                            programmeChoice.setIsWithdrawn(true);
                            programmeChoice.setIsWithdrawnApproved(false);
                            programmeChoice.setOfferStatusCd(Constants.PWA);
                            programmeChoice.setStatusValue(Constants.PENDING_WITHDRAW_APPROVAL);
                            programmeChoice = programmeChoiceService.save(programmeChoice);
                            programmeChoice = programmeChoiceService.get(programmeChoice.getId());
                            othersProgChoices.remove(i);
                            othersProgChoices.add(i, programmeChoice);
                            break;
                        }
                    }
                } else {
                    programmeChoice.setAdmFormProgId(firstProgChoice.getAdmFormProgId());
                    programmeChoice.setIsWithdrawn(true);
                    programmeChoice.setIsWithdrawnApproved(false);
                    programmeChoice.setOfferStatusCd(Constants.PWA);
                    programmeChoice.setStatusValue(Constants.PENDING_WITHDRAW_APPROVAL);
                    programmeChoiceService.save(programmeChoice);
                    if (StringUtils.isNotBlank(applicationId)) {
                        firstProgChoice = programmeChoiceService.getFirstChoiceByApplicationId(applicationId);
                    } else {
                        firstProgChoice = new ProgrammeChoiceVo();
                    }
                }
            }
        }
        if (!retrieveQualifications(Constants.HOUSING_MANAGEMENT, "")) {
            acadQualHousingMgmt.setIsSelectHousingMgmt(false);
            UIUtil.update("mainTab:formChoice:housingMgmtGroup", false);
        }
        if (!retrieveQualifications(Constants.BACHELOR_OF_NURSING, Constants.PART_TIME)) {
            acadQualNursingVo.setIsSelectNursing(false);
            UIUtil.update("mainTab:formChoice:nursingGroup", false);
        }
        if (!retrieveQualifications(Constants.BACHELOR_OF_ARTS, "")) {
            musicSpecialist.setIsDisplayBachelorOfArts(false);
            UIUtil.update("mainTab:formChoice:musicSpecialistGroup", false);
        }
        firstChoiceProgId = firstProgChoice.getAdmFormProgId();
        otherChoiceProgIds = new ArrayList<String>();
        for (ProgrammeChoiceVo othersProgChoice : othersProgChoices) {
            otherChoiceProgIds.add(othersProgChoice.getAdmFormProgId());
        }
        setFormProgDisabled();
    }

    public void resetOtherChoice(String id) {
        for (ProgrammeChoiceVo othersProgChoice : othersProgChoices) {
            if (id.equals(othersProgChoice.getId())) {
                othersProgChoices.remove(othersProgChoice);
                break;
            }
        }
        if (othersProgChoices.size() < 4) {
            for (int i = othersProgChoices.size(); i < 4; i++) {
                othersProgChoices.add(new ProgrammeChoiceVo());
            }
        }
    }

    public boolean validateContent() {
        boolean valid = true;
        for (int i = 0; i < othersProgChoices.size(); i++) {
            ProgrammeChoiceVo programmeChoiceVo = othersProgChoices.get(i);
            programmeChoiceVo.setAdmFormProgId(otherChoiceProgIds.get(i));
        }
        if (("0".equals(firstChoiceProgId) || StringUtils.isBlank(firstChoiceProgId)) && !isChoiceEndDate) {
            UIUtil.setInvalidDataTableSelectMenu("mainTab:formChoice:firstChoiceTable");
            valid = false;
        }
        if (sportScholarship.getIsActiveSport()) {
            if (StringUtils.isBlank(sportScholarship.getSptAppScheme())) {
                UIUtil.setInvalid(":mainTab:formChoice:sptAppScheme");
                valid = false;
            }
            if (StringUtils.isBlank(sportScholarship.getSptSports())) {
                UIUtil.setInvalid(":mainTab:formChoice:sptSports");
                valid = false;
            }
            if (sportScholarship.getSptLevels().size() <= 0) {
                UIUtil.setInvalid(":mainTab:formChoice:sptLevelMenu");
                valid = false;
            } else {
                if (sportScholarship.getSptLevels().contains(Constants.OTHERS)) {
                    if (StringUtils.isBlank(sportScholarship.getSptLevelOthers())) {
                        UIUtil.setInvalid(":mainTab:formChoice:sptLevelOthers");
                        valid = false;
                    }
                }
            }
        }
        if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else {
            return true;
        }
    }

    public void initAdmFormProgId() {
        firstProgChoice.setAdmFormProgId(firstChoiceProgId);
        for (int i = 0; i < othersProgChoices.size(); i++) {
            ProgrammeChoiceVo programmeChoiceVo = othersProgChoices.get(i);
            programmeChoiceVo.setAdmFormProgId(otherChoiceProgIds.get(i));
        }
    }

    public String choiceEndDateFormat() {
        String endDate = "";
        if (choiceEndDate != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd MMMMM, yyyy", InternationalizationBean.locale);
            endDate = df.format(choiceEndDate) + ".";
        }
        return endDate;
    }

    public void changeSptLevel() {
        boolean activeOthers = false;
        for (String sptLevel : sportScholarship.getSptLevels()) {
            if (Constants.OTHERS.equals(sptLevel)) {
                activeOthers = true;
                break;
            }
        }
        if (activeOthers) {
            sportScholarship.setIsActiveSptLevelOthers(true);
        } else {
            sportScholarship.setSptLevelOthers("");
            sportScholarship.setIsActiveSptLevelOthers(false);
        }
    }

    public void downloadTermsAndConditions(String scholarType) {
        String fileName = "";
        if ("UWC".equals(scholarType)) {
            fileName = "HKU-UWC Diversity Scholarships Application Form.pdf";
        } else if ("Nigerian".equals(scholarType)) {
            fileName = "Hon Ping Scholarships for Nigerian Students Application.pdf";
        }
        File file = new File(DOWNLOAD_PATH_SERVER + fileName);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            downloadFile = new DefaultStreamedContent(input, contentType, fileName);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public ProgrammeChoiceVo getFirstProgChoice() {
        return firstProgChoice;
    }

    public void setFirstProgChoice(ProgrammeChoiceVo firstProgChoice) {
        this.firstProgChoice = firstProgChoice;
    }

    public List<ProgrammeChoiceVo> getOthersProgChoices() {
        return othersProgChoices;
    }

    public void setOthersProgChoices(List<ProgrammeChoiceVo> othersProgChoices) {
        this.othersProgChoices = othersProgChoices;
    }

    public SpecialSchemeVo getSportScholarship() {
        return sportScholarship;
    }

    public void setSportScholarship(SpecialSchemeVo sportScholarship) {
        this.sportScholarship = sportScholarship;
    }

    public SpecialSchemeVo getMusicSpecialist() {
        return musicSpecialist;
    }

    public void setMusicSpecialist(SpecialSchemeVo musicSpecialist) {
        this.musicSpecialist = musicSpecialist;
    }

    public AcadQualHousingMgmtVo getAcadQualHousingMgmt() {
        return acadQualHousingMgmt;
    }

    public void setAcadQualHousingMgmt(AcadQualHousingMgmtVo acadQualHousingMgmt) {
        this.acadQualHousingMgmt = acadQualHousingMgmt;
    }

    public List<AcadQualOthersVo> getAcadQualOthers() {
        return acadQualOthers;
    }

    public void setAcadQualOthers(List<AcadQualOthersVo> acadQualOthers) {
        this.acadQualOthers = acadQualOthers;
    }

    public List<FormProgVo> getFormProgs() {
        return formProgs;
    }

    public void setFormProgs(List<FormProgVo> formProgs) {
        this.formProgs = formProgs;
    }

    public Date getChoiceEndDate() {
        return choiceEndDate;
    }

    public void setChoiceEndDate(Date choiceEndDate) {
        this.choiceEndDate = choiceEndDate;
    }

    public boolean getIsChoiceEndDate() {
        return isChoiceEndDate;
    }

    public void setIsChoiceEndDate(boolean isChoiceEndDate) {
        this.isChoiceEndDate = isChoiceEndDate;
    }

    public Date getAppEndDate() {
        return appEndDate;
    }

    public void setAppEndDate(Date appEndDate) {
        this.appEndDate = appEndDate;
    }

    public PersonalParticularsVo getPersonalParticularsVo() {
        return personalParticularsVo;
    }

    public void setPersonalParticularsVo(PersonalParticularsVo personalParticularsVo) {
        this.personalParticularsVo = personalParticularsVo;
    }

    public SpecialSchemeVo getSpecialist() {
        return specialist;
    }

    public void setSpecialist(SpecialSchemeVo specialist) {
        this.specialist = specialist;
    }

    public CountryVo getCountryVo() {
        return countryVo;
    }

    public void setCountryVo(CountryVo countryVo) {
        this.countryVo = countryVo;
    }

    public ScholarVo getHeForSheScholarVo() {
        return heForSheScholarVo;
    }

    public void setHeForSheScholarVo(ScholarVo heForSheScholarVo) {
        this.heForSheScholarVo = heForSheScholarVo;
    }

    public InstitutionVo getInstitutionVo() {
        return institutionVo;
    }

    public void setInstitutionVo(InstitutionVo institutionVo) {
        this.institutionVo = institutionVo;
    }

    public ScholarVo getUwcScholarVo() {
        return uwcScholarVo;
    }

    public void setUwcScholarVo(ScholarVo uwcScholarVo) {
        this.uwcScholarVo = uwcScholarVo;
    }

    public ScholarVo getAflScholarVo() {
        return aflScholarVo;
    }

    public void setAflScholarVo(ScholarVo aflScholarVo) {
        this.aflScholarVo = aflScholarVo;
    }

    public ApplicationVo getApplicationVo() {
        return applicationVo;
    }

    public void setApplicationVo(ApplicationVo applicationVo) {
        this.applicationVo = applicationVo;
    }

    public List<AcadQualHousingMgmtPqVo> getAcadQualHousingMgmtPqVos() {
        return acadQualHousingMgmtPqVos;
    }

    public void setAcadQualHousingMgmtPqVos(List<AcadQualHousingMgmtPqVo> acadQualHousingMgmtPqVos) {
        this.acadQualHousingMgmtPqVos = acadQualHousingMgmtPqVos;
    }

    public List<AcadQualHousingMgmtRweVo> getAcadQualHousingMgmtRweVos() {
        return acadQualHousingMgmtRweVos;
    }

    public void setAcadQualHousingMgmtRweVos(List<AcadQualHousingMgmtRweVo> acadQualHousingMgmtRweVos) {
        this.acadQualHousingMgmtRweVos = acadQualHousingMgmtRweVos;
    }

    public AcadQualNursingVo getAcadQualNursingVo() {
        return acadQualNursingVo;
    }

    public void setAcadQualNursingVo(AcadQualNursingVo acadQualNursingVo) {
        this.acadQualNursingVo = acadQualNursingVo;
    }

    public List<AcadQualNursingNrVo> getAcadQualNursingNrVos() {
        return acadQualNursingNrVos;
    }

    public void setAcadQualNursingNrVos(List<AcadQualNursingNrVo> acadQualNursingNrVos) {
        this.acadQualNursingNrVos = acadQualNursingNrVos;
    }

    public List<AcadQualNursingPrqVo> getAcadQualNursingPrqVos() {
        return acadQualNursingPrqVos;
    }

    public void setAcadQualNursingPrqVos(List<AcadQualNursingPrqVo> acadQualNursingPrqVos) {
        this.acadQualNursingPrqVos = acadQualNursingPrqVos;
    }

    public List<AcadQualNursingExpVo> getAcadQualNursingExpVos() {
        return acadQualNursingExpVos;
    }

    public void setAcadQualNursingExpVos(List<AcadQualNursingExpVo> acadQualNursingExpVos) {
        this.acadQualNursingExpVos = acadQualNursingExpVos;
    }

    public List<AcadQualNursingExpVo> getAcadQualNursingOtherExpVos() {
        return acadQualNursingOtherExpVos;
    }

    public void setAcadQualNursingOtherExpVos(List<AcadQualNursingExpVo> acadQualNursingOtherExpVos) {
        this.acadQualNursingOtherExpVos = acadQualNursingOtherExpVos;
    }

    public StreamedContent getDownloadFile() {
        return downloadFile;
    }
}
