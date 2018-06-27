package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.RequirementRelationshipService;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.service.exam.GradeService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.RequirementRelationshipVo;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.exam.GradeVo;
import com.accentrix.hku.vo.exam.SubjectVo;
import com.accentrix.hku.vo.exam.TypeVo;
import com.accentrix.hku.vo.xml.ExaminationVo;
import com.accentrix.hku.vo.xml.SubjectRequirementVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RequirementEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RequirementEditBean.class);

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private RequirementRelationshipService requirementRelationshipService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GradeService gradeService;

    private List<RequirementVo> childRequirements;

    private List<RequirementVo> allRequirements;

    private List<RequirementRelationshipVo> requirementRelationships;

    private String requirementId;

    private RequirementVo requirementVo;

    private RequirementVo addRequirementVo;

    private String addRequirementId;

    private boolean activeComposite;

    private boolean activePubExam;

    private boolean activeGps;

    private boolean activeAge;

    private List<TypeVo> examTypes;

    private List<SubjectVo> subjects;

    private List<GradeVo> grades;

    private SubjectRequirementVo subjectRequirement;

    private List<String> multiSubjectStrList;

    private List<String> examLevels;

    private List<String> examBoards;

    private boolean create;

    private boolean showExamLevel;

    private boolean showExamBoard;

    private boolean disableSubjects;

    private boolean examSatOrIeTo;

    private List<String> selectedSubs;

    private boolean showOutOf45;

    private boolean disableGrades;

    private boolean disableExamination;

    public RequirementEditBean() {
        init();
    }

    public void init() {
        LOG.info("EditRequiremen loading...");
        requirementId = (String) JSFUtil.getSessionMap().get("requirementId");
        retrieveData();
    }

    private void retrieveData() {
        if (StringUtils.isNotBlank(requirementId)) {
            requirementVo = requirementService.get(requirementId);
            if (requirementVo.getType().equals(Constants.COMPOSITE)) {
                activeComposite = true;
                allRequirements = requirementService.findList(new RequirementVo());
                removeFromRequirments(allRequirements, requirementVo.getId());

                requirementRelationships = requirementRelationshipService.findListByParentId(requirementId);
                childRequirements = new ArrayList<RequirementVo>();
                for (RequirementRelationshipVo requirementRelationshipVo : requirementRelationships) {
                    RequirementVo requirementVo = requirementService
                            .get(requirementRelationshipVo.getChildRequirementId());
                    childRequirements.add(requirementVo);
                    removeFromRequirments(allRequirements, requirementVo.getId());
                }
            } else if (requirementVo.getType().equals(Constants.PUBLICEXAM)) {
                activePubExam = true;
                examTypes = typeService.findList();
                requirementVo.setExamination(
                        requirementVo.getExamination() == null ? new ExaminationVo() : requirementVo.getExamination());
                if (CollectionUtils.isEmpty(requirementVo.getExamination().getSubjectRequirements())) {
                    disableExamination = false;
                } else {
                    disableExamination = true;
                }
            } else if (requirementVo.getType().equals(Constants.GPS)) {
                activeGps = true;
                examTypes = typeService.findList();
                renderedOutOf45();
            } else if (requirementVo.getType().equals(Constants.AGE))
                activeAge = true;
        }
    }

    private void removeFromRequirments(List<RequirementVo> requirements, String id) {
        for (RequirementVo vo : requirements) {
            if (vo.getId().equals(id)) {
                requirements.remove(vo);
                break;
            }
        }
    }

    public void addSubRequirement() {
        if (StringUtils.isNotBlank(addRequirementId)) {
            RequirementVo requirementVo = requirementService.get(addRequirementId);
            childRequirements.add(requirementVo);
            removeFromRequirments(allRequirements, addRequirementId);
            addRequirementId = "";
            UIUtil.hide("requirementDialog");
        } else {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.SUBSIDIARY_REQUIREMENT_MISSING,
                    Constants.SUBSIDIARY_REQUIREMENT_MISSING_CHI);
            UIUtil.setInvalid("addRequirement:requirementName");
        }
    }

    public void removeSubRequirement(String id) {
        if (StringUtils.isNotBlank(id)) {
            removeFromRequirments(childRequirements, id);
            allRequirements = requirementService.findList(new RequirementVo());
            removeFromRequirments(allRequirements, requirementVo.getId());
            for (RequirementVo subVo : childRequirements) {
                removeFromRequirments(allRequirements, subVo.getId());
            }
        }
    }

    public void save() {
        if (validateContent()) {
            if (activeComposite) {
                saveCompositeRequirementRelations();
            }
            requirementService.save(requirementVo);
            disableExamination = true;
            UIUtil.displaySaveSuccessDialog(ConstantCommon.LOCALE_UK);
        }
    }

    private boolean validateContent() {
        boolean valid = true;
        boolean noRequirementRecord = false;
        if (activeComposite) {
            if (CollectionUtils.isEmpty(childRequirements) || childRequirements.size() < 2) {
                UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.SUBSIDIARY_REQUIREMENT_MISSING,
                        Constants.SUBSIDIARY_REQUIREMENT_MISSING_CHI);
                noRequirementRecord = true;
            }
            if (StringUtils.isBlank(requirementVo.getRelationship())) {
                UIUtil.setInvalid("requirementDetails:requirementTab:composite");
                valid = false;
            }
        }
        if (activePubExam) {
            if (CollectionUtils.isEmpty(requirementVo.getExamination().getSubjectRequirements())) {
                UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.SUBJECT_REQUIREMENT_MISSING,
                        Constants.SUBJECT_REQUIREMENT_MISSING_CHI);
                noRequirementRecord = true;
            }
            if (!CollectionUtils.isEmpty(requirementVo.getExamination().getSubjectRequirements())
                    && requirementVo.getExamination().getSubjectRequirements().size() > 1
                    && StringUtils.isBlank(requirementVo.getRelationship())) {
                UIUtil.setInvalid("requirementDetails:requirementTab:relationship");
                valid = false;
            }
            if (StringUtils.isBlank(requirementVo.getExamination().getExamination())) {
                UIUtil.setInvalid("requirementDetails:requirementTab:examination");
                valid = false;
            }
        }
        if (activeAge) {
            if (requirementVo.getAgeRange().getAgeFrom() == null && requirementVo.getAgeRange().getAgeTo() == null) {
                UIUtil.setInvalid("requirementDetails:requirementTab:ageFrom");
                UIUtil.setInvalid("requirementDetails:requirementTab:ageTo");
                valid = false;
            }
        }
        if (activeGps) {
            if (StringUtils.isBlank(requirementVo.getGpsVo().getExamination())) {
                UIUtil.setInvalid("requirementDetails:requirementTab:examForGps");
                valid = false;
            }
            if (requirementVo.getGpsVo().getMinScore() == null) {
                UIUtil.setInvalid("requirementDetails:requirementTab:minScore");
                valid = false;
            }
        }
        if (!valid) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        }
        if (noRequirementRecord) {
            return false;
        }
        return true;
    }

    private void saveCompositeRequirementRelations() {
        for (RequirementRelationshipVo requirementRelationshipVo : requirementRelationships) {
            boolean delete = true;
            for (RequirementVo subVo : childRequirements) {
                if (requirementRelationshipVo.getChildRequirementId().equals(subVo.getId())) {
                    delete = false;
                    break;
                }
            }
            if (delete) {
                requirementRelationshipService.delete(requirementRelationshipVo.getId());
            }
        }
        for (RequirementVo subVo : childRequirements) {
            boolean add = true;
            for (RequirementRelationshipVo requirementRelationshipVo : requirementRelationships) {
                if (requirementRelationshipVo.getChildRequirementId().equals(subVo.getId())) {
                    add = false;
                    break;
                }
            }
            if (add) {
                RequirementRelationshipVo relationVo = new RequirementRelationshipVo();
                relationVo.setParentRequirementId(requirementId);
                relationVo.setChildRequirementId(subVo.getId());
                requirementRelationshipService.save(relationVo);
            }
        }
        requirementRelationships = requirementRelationshipService.findListByParentId(requirementId);
    }

    public void loadSubjectRequirementDialog(SubjectRequirementVo subjectReq) {
        if (!checkExaminationSelection())
            return;
        selectedSubs = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(requirementVo.getExamination().getSubjectRequirements())) {
            for (SubjectRequirementVo vo : requirementVo.getExamination().getSubjectRequirements()) {
                if ("OR".equals(vo.getRelationship()))
                    selectedSubs.addAll(Arrays.asList(vo.getSubject().split(" / ")));
                else if ("AND".equals(vo.getRelationship()))
                    selectedSubs.addAll(Arrays.asList(vo.getSubject().split(" & ")));
                else
                    selectedSubs.add(vo.getSubject());
            }
        }

        multiSubjectStrList = new ArrayList<String>();
        if (subjectReq != null) {
            subjectReq.setInEdit(true);
            subjectRequirement = new SubjectRequirementVo();
            subjectRequirement = cloneSubjectRequirement(subjectReq, subjectRequirement);
            String split = null;
            if ("OR".equals(subjectReq.getRelationship()))
                split = " / ";
            else if ("AND".equals(subjectReq.getRelationship()))
                split = " & ";
            if (StringUtils.isNotBlank(split)) {
                for (String subjectStr : subjectReq.getSubject().split(split)) {
                    multiSubjectStrList.add(subjectStr);
                }
            } else
                multiSubjectStrList.add(subjectReq.getSubject());
            create = false;
        } else {
            this.subjectRequirement = new SubjectRequirementVo();
            create = true;
        }

        subjects = null;
        renderedMultiSelectSubjects();
        UIUtil.show("subjectRequirementDialog");
    }

    private SubjectRequirementVo cloneSubjectRequirement(SubjectRequirementVo fromVo, SubjectRequirementVo toVo) {
        toVo.setExamLevel(fromVo.getExamLevel());
        toVo.setExamBoard(fromVo.getExamBoard());
        toVo.setSubject(fromVo.getSubject());
        toVo.setRelationship(fromVo.getRelationship());
        toVo.setGrade(fromVo.getGrade());
        return toVo;
    }

    public void renderedMultiSelectSubjects() {
        TypeVo examType = typeService.get(requirementVo.getExamination().getExamination());

        showExamLevel = false;
        showExamBoard = false;
        disableSubjects = false;
        if (Constants.EXAM_GCE_CODES.contains(examType.getExamCd())) {
            showExamLevel = true;
            showExamBoard = true;
            examSatOrIeTo = false;
            if (StringUtils.isBlank(subjectRequirement.getExamBoard())
                    || StringUtils.isBlank(subjectRequirement.getExamLevel()))
                disableSubjects = true;
            else
                disableSubjects = false;
        }

        if (Constants.EXAM_SAT_CODES.contains(examType.getExamCd())) {
            showExamLevel = true;
            showExamBoard = false;
            examSatOrIeTo = true;
            if (StringUtils.isBlank(subjectRequirement.getExamLevel()))
                disableSubjects = true;
            else
                disableSubjects = false;
        }

        if (Constants.EXAM_IT_CODES.contains(examType.getExamCd())) {
            showExamLevel = false;
            showExamBoard = false;
            disableSubjects = false;
            examSatOrIeTo = true;
        }

        if (Constants.EXAM_IB_CODES.contains(examType.getExamCd())) {
            showExamLevel = true;
            showExamBoard = false;
            examSatOrIeTo = false;
            if (StringUtils.isBlank(subjectRequirement.getExamLevel()))
                disableSubjects = true;
            else
                disableSubjects = false;
        }

        if (showExamBoard)
            examBoards = subjectService.findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(
                    requirementVo.getExamination().getExamination(), subjectRequirement.getExamLevel());
        if (showExamLevel)
            examLevels = subjectService.findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(
                    requirementVo.getExamination().getExamination(), subjectRequirement.getExamBoard());
        if (!disableSubjects) {
            if (subjects != null)
                multiSubjectStrList = new ArrayList<String>();
            subjects = subjectService.findByExamTypeIdAndExamBoardAndExamLevel(
                    requirementVo.getExamination().getExamination(), subjectRequirement.getExamBoard(),
                    subjectRequirement.getExamLevel());
        }

        if (Constants.EXAM_GCE_GRADE_LEVELS.contains(examType.getExamCd())) {
            if (StringUtils.isBlank(subjectRequirement.getExamLevel()))
                disableGrades = true;
            else {
                grades = gradeService.getByExamTypeIdAndExamLevel(requirementVo.getExamination().getExamination(),
                        subjectRequirement.getExamLevel());
                disableGrades = false;
            }
        } else
            grades = gradeService.getByExamTypeId(requirementVo.getExamination().getExamination());
    }

    public void addSubjectRequirement() {
        if (!validateSubjectRequirement())
            return;
        String split = "";
        if ("OR".equals(subjectRequirement.getRelationship()))
            split = " / ";
        else if ("AND".equals(subjectRequirement.getRelationship()))
            split = " & ";
        String subjectStr = "";
        for (String str : multiSubjectStrList) {
            if (multiSubjectStrList.indexOf(str) < multiSubjectStrList.size() - 1)
                subjectStr += str + split;
            else
                subjectStr += str;
        }
        subjectRequirement.setSubject(subjectStr);
        if (create) {
            if (CollectionUtils.isEmpty(requirementVo.getExamination().getSubjectRequirements()))
                requirementVo.getExamination().setSubjectRequirements(new ArrayList<SubjectRequirementVo>());
            requirementVo.getExamination().getSubjectRequirements().add(subjectRequirement);
        } else {
            for (SubjectRequirementVo subReq : requirementVo.getExamination().getSubjectRequirements()) {
                if (subReq.isInEdit()) {
                    subReq = cloneSubjectRequirement(subjectRequirement, subReq);
                }
            }
        }
        multiSubjectStrList = new ArrayList<String>();
        subjectRequirement = new SubjectRequirementVo();
        UIUtil.hide("subjectRequirementDialog");
    }

    private boolean validateSubjectRequirement() {
        boolean valid = true;
        if (showExamLevel && StringUtils.isBlank(subjectRequirement.getExamLevel())) {
            UIUtil.setInvalid("subjectRequirementDialog:examLevel");
            valid = false;
        }
        if (showExamBoard && StringUtils.isBlank(subjectRequirement.getExamBoard())) {
            UIUtil.setInvalid("subjectRequirementDialog:examBoard");
            valid = false;
        }
        if (CollectionUtils.isEmpty(multiSubjectStrList)) {
            UIUtil.setInvalid("subjectRequirementDialog:subjects");
            valid = false;
        }
        if (!CollectionUtils.isEmpty(multiSubjectStrList) && multiSubjectStrList.size() > 1
                && StringUtils.isBlank(subjectRequirement.getRelationship())) {
            UIUtil.setInvalid("subjectRequirementDialog:relationship");
            valid = false;
        }
        if (StringUtils.isBlank(subjectRequirement.getGrade())) {
            if (examSatOrIeTo)
                UIUtil.setInvalid("subjectRequirementDialog:score");
            else
                UIUtil.setInvalid("subjectRequirementDialog:grade");
            valid = false;
        }
        if (!valid)
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        return valid;
    }

    public void removeSubjectRequirement(SubjectRequirementVo subjectRequirement) {
        requirementVo.getExamination().getSubjectRequirements().remove(subjectRequirement);
    }

    public void edit(String id) {
        JSFUtil.getSessionMap().put("requirementId", id);
    }

    private boolean checkExaminationSelection() {
        if (StringUtils.isBlank(requirementVo.getExamination().getExamination())) {
            UIUtil.setInvalid("requirementDetails:requirementTab:examination");
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        }
        return true;
    }

    public void renderedOutOf45() {
        if (StringUtils.isNotBlank(requirementVo.getGpsVo().getExamination())) {
            TypeVo examType = typeService.get(requirementVo.getGpsVo().getExamination());
            if ("IBD".equals(examType.getExamCd()))
                showOutOf45 = true;
            else
                showOutOf45 = false;
        }
    }

    public void cleanSubjectRequirements(RequirementVo requirementVo) {
        requirementVo.getExamination().setSubjectRequirements(new ArrayList<SubjectRequirementVo>());
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public RequirementVo getRequirementVo() {
        return requirementVo;
    }

    public void setRequirementVo(RequirementVo requirementVo) {
        this.requirementVo = requirementVo;
    }

    public RequirementService getRequirementService() {
        return requirementService;
    }

    public void setRequirementService(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    public boolean isActiveComposite() {
        return activeComposite;
    }

    public void setActiveComposite(boolean activeComposite) {
        this.activeComposite = activeComposite;
    }

    public boolean isActivePubExam() {
        return activePubExam;
    }

    public void setActivePubExam(boolean activePubExam) {
        this.activePubExam = activePubExam;
    }

    public boolean isActiveAge() {
        return activeAge;
    }

    public void setActiveAge(boolean activeAge) {
        this.activeAge = activeAge;
    }

    public List<RequirementVo> getChildRequirements() {
        return childRequirements;
    }

    public void setChildRequirements(List<RequirementVo> childRequirements) {
        this.childRequirements = childRequirements;
    }

    public List<RequirementVo> getAllRequirements() {
        return allRequirements;
    }

    public void setAllRequirements(List<RequirementVo> allRequirements) {
        this.allRequirements = allRequirements;
    }

    public List<RequirementRelationshipVo> getRequirementRelationships() {
        return requirementRelationships;
    }

    public void setRequirementRelationships(List<RequirementRelationshipVo> requirementRelationships) {
        this.requirementRelationships = requirementRelationships;
    }

    public RequirementVo getAddRequirementVo() {
        return addRequirementVo;
    }

    public void setAddRequirementVo(RequirementVo addRequirementVo) {
        this.addRequirementVo = addRequirementVo;
    }

    public String getAddRequirementId() {
        return addRequirementId;
    }

    public void setAddRequirementId(String addRequirementId) {
        this.addRequirementId = addRequirementId;
    }

    public List<TypeVo> getExamTypes() {
        return examTypes;
    }

    public List<SubjectVo> getSubjects() {
        return subjects;
    }

    public SubjectRequirementVo getSubjectRequirement() {
        return subjectRequirement;
    }

    public void setSubjectRequirement(SubjectRequirementVo subjectRequirement) {
        this.subjectRequirement = subjectRequirement;
    }

    public List<GradeVo> getGrades() {
        return grades;
    }

    public List<String> getMultiSubjectStrList() {
        return multiSubjectStrList;
    }

    public void setMultiSubjectStrList(List<String> multiSubjectStrList) {
        this.multiSubjectStrList = multiSubjectStrList;
    }

    public boolean isActiveGps() {
        return activeGps;
    }

    public void setActiveGps(boolean activeGps) {
        this.activeGps = activeGps;
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

    public List<String> getSelectedSubs() {
        return selectedSubs;
    }

    public void setSelectedSubs(List<String> selectedSubs) {
        this.selectedSubs = selectedSubs;
    }

    public boolean isExamSatOrIeTo() {
        return examSatOrIeTo;
    }

    public boolean isShowExamLevel() {
        return showExamLevel;
    }

    public void setShowExamLevel(boolean showExamLevel) {
        this.showExamLevel = showExamLevel;
    }

    public boolean isShowOutOf45() {
        return showOutOf45;
    }

    public boolean isDisableGrades() {
        return disableGrades;
    }

    public boolean getDisableExamination() {
        return disableExamination;
    }

    public void setDisableExamination(boolean disableExamination) {
        this.disableExamination = disableExamination;
    }
}
