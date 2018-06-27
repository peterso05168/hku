package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.FormProgReqService;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.adm.GpaFormulaItmService;
import com.accentrix.hku.service.adm.GpsScoringSubjectService;
import com.accentrix.hku.service.adm.ScoringFormulaService;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.FormProgReqVo;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.adm.GpaFormulaItmVo;
import com.accentrix.hku.vo.adm.GpsScoringSubjectVo;
import com.accentrix.hku.vo.adm.ScoringFormulaVo;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.exam.SubjectVo;
import com.accentrix.hku.vo.exam.TypeVo;

/**
 * @author Lonny Wei
 * @date 2018年4月2日 上午10:36:23
 * @version 1.0
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ProgrammeEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private FormProgService formProgService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private ScoringFormulaService scoringFormulaService;

    @Autowired
    private GpaFormulaItmService gpaFormulaItmService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private FormProgReqService formProgReqService;

    @Autowired
    private GpsScoringSubjectService gpsScoringSubjectService;

    private String programmeId;

    private FormProgVo formProgVo;

    private List<RequirementVo> requirements;

    private List<String> requirementIds;

    private List<ScoringFormulaVo> scoringFormulas;

    private List<String> existExamTypeIds;

    private ScoringFormulaVo scoringFormulaVo;

    private List<TypeVo> examTypes;

    private List<SubjectVo> subjectVos;

    private List<String> includingSubjectIds;

    private List<String> excludingSubjectIds;

    private List<GpaFormulaItmVo> gpaFormulaItms;

    private GpaFormulaItmVo gpaFormulaItmVo;

    private List<String> examLevels;

    private List<String> examBoards;

    private boolean createFormulaItm;

    private boolean disableSubjects;

    private boolean showExamBoard;

    private boolean showExamLevel;

    private String examTypeId;

    private boolean disableExamType;

    public ProgrammeEditBean() {
        init();
    }

    public void init() {
        programmeId = (String) JSFUtil.getSessionMap().get("formProgId");
        if (StringUtils.isNotBlank(programmeId)) {
            formProgVo = formProgService.get(programmeId);
        }
        RequirementVo reqSearchVo = new RequirementVo();
        reqSearchVo.setIsPublished(true);
        requirements = requirementService.findList(reqSearchVo);
        requirementIds = requirementService.getIdsByFormProgId(formProgVo.getId());
        scoringFormulas = scoringFormulaService.findByFormProgId(formProgVo.getId());
        scoringFormulaVo = new ScoringFormulaVo();
    }

    private void existExamTypeIds() {
        existExamTypeIds = new ArrayList<String>();
        for (ScoringFormulaVo scoringFormula : scoringFormulas) {
            existExamTypeIds.add(scoringFormula.getExamTypeId());
        }
        for (TypeVo type : examTypes) {
            if (existExamTypeIds.contains(type.getId()))
                type.setDisabled(true);
        }
    }

    public void loadScoringFormulaDialog() {
        gpaFormulaItms = new ArrayList<GpaFormulaItmVo>();
        examTypes = typeService.findList();
        existExamTypeIds();
        examTypeId = null;
        disableExamType = false;
        if (scoringFormulaVo != null) {
            scoringFormulaVo = scoringFormulaService.get(scoringFormulaVo.getId());
            if ("GPS".equals(scoringFormulaVo.getFormulaType())) {
                examTypeId = scoringFormulaVo.getExamTypeId();
                disableExamType = true;
                renderedMultiSelectSubjects();
                includingSubjectIds = subjectService.getIdsByScoringFormulaId(scoringFormulaVo.getId(), "IN");
                excludingSubjectIds = subjectService.getIdsByScoringFormulaId(scoringFormulaVo.getId(), "EX");
            } else if ("GPA".equals(scoringFormulaVo.getFormulaType())) {
                gpaFormulaItms = gpaFormulaItmService.findByScoringFormulaId(scoringFormulaVo.getId());
            }
        } else {
            scoringFormulaVo = new ScoringFormulaVo();
            includingSubjectIds = new ArrayList<String>();
            excludingSubjectIds = new ArrayList<String>();
        }
    }

    public void renderedMultiSelectSubjects() {
        if (StringUtils.isNotBlank(scoringFormulaVo.getExamTypeId())) {
            TypeVo examType = typeService.get(scoringFormulaVo.getExamTypeId());
            showExamLevel = false;
            showExamBoard = false;
            disableSubjects = false;
            if (Constants.EXAM_GCE_CODES.contains(examType.getExamCd())) {
                showExamLevel = true;
                showExamBoard = true;
                if (StringUtils.isBlank(scoringFormulaVo.getExamBoard())
                        || StringUtils.isBlank(scoringFormulaVo.getExamLevel()))
                    disableSubjects = true;
                else
                    disableSubjects = false;
            }

            if (Constants.EXAM_SAT_CODES.contains(examType.getExamCd())) {
                showExamLevel = true;
                showExamBoard = false;
                if (StringUtils.isBlank(scoringFormulaVo.getExamLevel()))
                    disableSubjects = true;
                else
                    disableSubjects = false;
            }

            if (Constants.EXAM_IT_CODES.contains(examType.getExamCd())) {
                showExamLevel = false;
                showExamBoard = false;
                disableSubjects = false;
            }

            if (Constants.EXAM_IB_CODES.contains(examType.getExamCd())) {
                showExamLevel = true;
                showExamBoard = false;
                if (StringUtils.isBlank(scoringFormulaVo.getExamLevel()))
                    disableSubjects = true;
                else
                    disableSubjects = false;
            }
        } else {
            showExamBoard = false;
            showExamLevel = false;
            disableSubjects = true;
        }

        if (showExamBoard)
            examBoards = subjectService.findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(
                    scoringFormulaVo.getExamTypeId(), scoringFormulaVo.getExamLevel());
        else
            scoringFormulaVo.setExamBoard(null);

        if (showExamLevel)
            examLevels = subjectService.findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(
                    scoringFormulaVo.getExamTypeId(), scoringFormulaVo.getExamBoard());
        else
            scoringFormulaVo.setExamLevel(null);

        if ((StringUtils.isNotBlank(scoringFormulaVo.getExamBoard())
                && !examBoards.contains(scoringFormulaVo.getExamBoard()))
                || (StringUtils.isNotBlank(scoringFormulaVo.getExamLevel())
                        && !examLevels.contains(scoringFormulaVo.getExamLevel()))) {
            disableSubjects = true;
        }

        includingSubjectIds = new ArrayList<String>();
        excludingSubjectIds = new ArrayList<String>();
        subjectVos = subjectService.findByExamTypeIdAndExamBoardAndExamLevel(scoringFormulaVo.getExamTypeId(),
                scoringFormulaVo.getExamBoard(), scoringFormulaVo.getExamLevel());
    }

    public void saveScoringFormula() {
        if (!validateSoringFormula())
            return;
        scoringFormulaVo.setAdmFormProgId(formProgVo.getId());
        scoringFormulaVo
                .setExamTypeId("".equals(scoringFormulaVo.getExamTypeId()) ? null : scoringFormulaVo.getExamTypeId());
        boolean relateFormulaItm = StringUtils.isBlank(scoringFormulaVo.getId());
        scoringFormulaVo = scoringFormulaService.save(scoringFormulaVo);
        if ("GPS".equals(scoringFormulaVo.getFormulaType())) {
            List<GpsScoringSubjectVo> deleteVos = new ArrayList<GpsScoringSubjectVo>();
            deleteVos.addAll(gpsScoringSubjectService.findByScoringFormulaId(scoringFormulaVo.getId(), "IN"));
            deleteVos.addAll(gpsScoringSubjectService.findByScoringFormulaId(scoringFormulaVo.getId(), "EX"));
            for (GpsScoringSubjectVo vo : deleteVos) {
                gpsScoringSubjectService.delete(vo.getId());
            }
            if (CollectionUtils.isNotEmpty(includingSubjectIds)) {
                for (String subjectId : includingSubjectIds) {
                    GpsScoringSubjectVo gpsSub = new GpsScoringSubjectVo();
                    gpsSub.setAdmScoringFormulaId(scoringFormulaVo.getId());
                    gpsSub.setExamSubjectId(subjectId);
                    gpsSub.setType("IN");
                    gpsScoringSubjectService.save(gpsSub);
                }
            }
            if (CollectionUtils.isNotEmpty(excludingSubjectIds)) {
                for (String subjectId : excludingSubjectIds) {
                    GpsScoringSubjectVo gpsSub = new GpsScoringSubjectVo();
                    gpsSub.setAdmScoringFormulaId(scoringFormulaVo.getId());
                    gpsSub.setExamSubjectId(subjectId);
                    gpsSub.setType("EX");
                    gpsScoringSubjectService.save(gpsSub);
                }
            }
        } else if ("GPA".equals(scoringFormulaVo.getFormulaType())) {
            if (CollectionUtils.isNotEmpty(gpaFormulaItms) && relateFormulaItm) {
                for (GpaFormulaItmVo formulaItm : gpaFormulaItms) {
                    formulaItm.setAdmScoringFormulaId(scoringFormulaVo.getId());
                    gpaFormulaItmService.save(formulaItm);
                }
            }
        }
        scoringFormulas = scoringFormulaService.findByFormProgId(formProgVo.getId());
        UIUtil.update("entranceRequirementForm", false);
        UIUtil.hide("scoringFormulaDialog");
    }

    private boolean validateSoringFormula() {
        if (StringUtils.isNotBlank(examTypeId) && StringUtils.isEmpty(scoringFormulaVo.getExamTypeId())) {
            scoringFormulaVo.setExamTypeId(examTypeId);
        }

        boolean valid = true;
        boolean validFormulaItm = true;
        if (StringUtils.isBlank(scoringFormulaVo.getFormulaName())) {
            UIUtil.setInvalid("scoringFormulaForm:formulaName");
            valid = false;
        }
        if (StringUtils.isBlank(scoringFormulaVo.getFormulaType())) {
            UIUtil.setInvalid("scoringFormulaForm:formulaType");
            valid = false;
        }
        if ("GPS".equals(scoringFormulaVo.getFormulaType())) {
            if (StringUtils.isBlank(scoringFormulaVo.getExamTypeId())) {
                UIUtil.setInvalid("scoringFormulaForm:examination");
                valid = false;
            }
            if (showExamBoard && StringUtils.isBlank(scoringFormulaVo.getExamBoard())) {
                UIUtil.setInvalid("scoringFormulaForm:examBoard");
                valid = false;
            }
            if (showExamLevel && StringUtils.isBlank(scoringFormulaVo.getExamLevel())) {
                UIUtil.setInvalid("scoringFormulaForm:examLevel");
                valid = false;
            }
            if (CollectionUtils.isEmpty(includingSubjectIds)) {
                UIUtil.setInvalid("scoringFormulaForm:including");
                valid = false;
            }
        } else if ("GPA".equals(scoringFormulaVo.getFormulaType())) {
            if (CollectionUtils.isEmpty(gpaFormulaItms)) {
                UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_GPA_FORMULA_ITEM_RECORD,
                        Constants.NO_GPA_FORMULA_ITEM_RECORD_CHI);
                validFormulaItm = false;
            }
        }
        if (!valid)
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        return valid && validFormulaItm;
    }

    public void loadGpaFormulaItm() {
        if (createFormulaItm) {
            gpaFormulaItmVo = new GpaFormulaItmVo();
            gpaFormulaItmVo.setAdmScoringFormulaId(scoringFormulaVo.getId());
        } else
            gpaFormulaItmVo = gpaFormulaItmService.get(gpaFormulaItmVo.getId());
        UIUtil.setValid("formulaItmForm:maxcGPA");
        UIUtil.setValid("formulaItmForm:minYearcGPA");
        UIUtil.setValid("formulaItmForm:minFinalYearcGPA");
        UIUtil.update("formulaItmForm", false);
    }

    public void saveGpaFormulaItm() {
        if (validateGpaFormulaItm()) {
            if (StringUtils.isNotBlank(scoringFormulaVo.getId())) {
                gpaFormulaItmVo = gpaFormulaItmService.save(gpaFormulaItmVo);
                gpaFormulaItms = gpaFormulaItmService.findByScoringFormulaId(scoringFormulaVo.getId());
            } else {
                gpaFormulaItms.add(gpaFormulaItmVo);
            }
            UIUtil.update("scoringFormulaForm", false);
            UIUtil.hide("GPAFormulaItemDialog");
        }
    }

    private boolean validateGpaFormulaItm() {
        boolean valid = true;
        if (StringUtils.isBlank(gpaFormulaItmVo.getMaxCgpa())) {
            UIUtil.setInvalid("formulaItmForm:maxcGPA");
            valid = false;
        }
        if (StringUtils.isBlank(gpaFormulaItmVo.getMinYearOneCgpa())) {
            UIUtil.setInvalid("formulaItmForm:minYearcGPA");
            valid = false;
        }
        if (StringUtils.isBlank(gpaFormulaItmVo.getMinFinalYearCgpa())) {
            UIUtil.setInvalid("formulaItmForm:minFinalYearcGPA");
            valid = false;
        }
        if (!valid)
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        return valid;
    }

    public void removeGpaFormulaItm() {
        gpaFormulaItms.remove(gpaFormulaItmVo);
    }

    public void save() {
        if (!saveValidation())
            return;
        formProgVo = formProgService.save(formProgVo);
        List<FormProgReqVo> progReqVos = formProgReqService.getByFormProgId(formProgVo.getId());
        if (CollectionUtils.isNotEmpty(progReqVos)) {
            for (FormProgReqVo progReqVo : progReqVos) {
                formProgReqService.delete(progReqVo.getId());
            }
        }
        for (String reqId : requirementIds) {
            FormProgReqVo progReqVo = new FormProgReqVo();
            progReqVo.setAdmFormProgId(formProgVo.getId());
            progReqVo.setAppRequirementId(reqId);
            formProgReqService.save(progReqVo);
        }
        UIUtil.displaySaveSuccessDialog(ConstantCommon.LOCALE_UK);
    }

    private boolean saveValidation() {
        boolean valid = true;
        if (CollectionUtils.isEmpty(requirementIds) && CollectionUtils.isEmpty(scoringFormulas)) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_REQUIREMENT_FORMULA_RECORD,
                    Constants.NO_REQUIREMENT_FORMULA_RECORD_CHI);
            valid = false;
        }
        return valid;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public FormProgVo getFormProgVo() {
        return formProgVo;
    }

    public void setFormProgVo(FormProgVo formProgVo) {
        this.formProgVo = formProgVo;
    }

    public List<RequirementVo> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementVo> requirements) {
        this.requirements = requirements;
    }

    public List<ScoringFormulaVo> getScoringFormulas() {
        return scoringFormulas;
    }

    public void setScoringFormulas(List<ScoringFormulaVo> scoringFormulas) {
        this.scoringFormulas = scoringFormulas;
    }

    public ScoringFormulaVo getScoringFormulaVo() {
        return scoringFormulaVo;
    }

    public void setScoringFormulaVo(ScoringFormulaVo scoringFormulaVo) {
        this.scoringFormulaVo = scoringFormulaVo;
    }

    public List<TypeVo> getExamTypes() {
        return examTypes;
    }

    public void setExamTypes(List<TypeVo> examTypes) {
        this.examTypes = examTypes;
    }

    public List<SubjectVo> getSubjectVos() {
        return subjectVos;
    }

    public void setSubjectVos(List<SubjectVo> subjectVos) {
        this.subjectVos = subjectVos;
    }

    public List<String> getIncludingSubjectIds() {
        return includingSubjectIds;
    }

    public void setIncludingSubjectIds(List<String> includingSubjectIds) {
        this.includingSubjectIds = includingSubjectIds;
    }

    public List<String> getExcludingSubjectIds() {
        return excludingSubjectIds;
    }

    public void setExcludingSubjectIds(List<String> excludingSubjectIds) {
        this.excludingSubjectIds = excludingSubjectIds;
    }

    public List<GpaFormulaItmVo> getGpaFormulaItms() {
        return gpaFormulaItms;
    }

    public void setGpaFormulaItms(List<GpaFormulaItmVo> gpaFormulaItms) {
        this.gpaFormulaItms = gpaFormulaItms;
    }

    public GpaFormulaItmVo getGpaFormulaItmVo() {
        return gpaFormulaItmVo;
    }

    public void setGpaFormulaItmVo(GpaFormulaItmVo gpaFormulaItmVo) {
        this.gpaFormulaItmVo = gpaFormulaItmVo;
    }

    public List<String> getRequirementIds() {
        return requirementIds;
    }

    public void setRequirementIds(List<String> requirementIds) {
        this.requirementIds = requirementIds;
    }

    public boolean isCreateFormulaItm() {
        return createFormulaItm;
    }

    public void setCreateFormulaItm(boolean createFormulaItm) {
        this.createFormulaItm = createFormulaItm;
    }

    public List<String> getExamLevels() {
        return examLevels;
    }

    public List<String> getExamBoards() {
        return examBoards;
    }

    public boolean isDisableSubjects() {
        return disableSubjects;
    }

    public boolean isShowExamBoard() {
        return showExamBoard;
    }

    public boolean isShowExamLevel() {
        return showExamLevel;
    }

    public boolean isDisableExamType() {
        return disableExamType;
    }

    public void setDisableExamType(boolean disableExamType) {
        this.disableExamType = disableExamType;
    }

}
