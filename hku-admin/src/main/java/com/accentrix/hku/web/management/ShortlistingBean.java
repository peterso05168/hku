package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.BestExamSubjRsltService;
import com.accentrix.hku.service.app.BestExamSubjService;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.service.app.IbdBestExamService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.app.QualificationRsltService;
import com.accentrix.hku.service.app.QualificationService;
import com.accentrix.hku.service.exam.GradeService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;
import com.accentrix.hku.vo.app.BestExamSubjVo;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.vo.app.IbdBestExamVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.app.QualificationRsltVo;
import com.accentrix.hku.vo.app.QualificationVo;
import com.accentrix.hku.vo.exam.GradeVo;
import com.accentrix.hku.vo.exam.TypeVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月16日 上午11:30:35
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ShortlistingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ShortlistingBean.class);

    @Autowired
    private HkuProgrammeService hkuProgrammeService;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private BestExamSubjService bestExamSubjService;
    @Autowired
    private BestExamSubjRsltService bestExamSubjRsltService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private QualificationRsltService qualificationRsltService;
    @Autowired
    private IbdBestExamService ibdBestExamService;

    private ProgrammeChoiceVo programmeChoiceVo;
    private LazyDataModel<ProgrammeChoiceVo> lazyDataModel;
    private List<String> facultyCodes;
    private List<HkuProgrammeVo> hkuProgrammeVos;
    private boolean status;
    private String facultyCode;
    private List<ProgrammeChoiceVo> programmeChoiceVos;
    private String hkuProgrammeDesc;
    private String searchDate;
    private boolean disableDeselect;
    private boolean disableSelect;
    private List<RefCdVo> opQualifications;
    private List<String> selectedQualifications;
    private String predictedActual;
    private List<RefCdVo> offerStatus;

    public ShortlistingBean() {
        LOG.debug("ShortlistingBean init...");
        init();
    }

    private void init() {
        programmeChoiceVo = new ProgrammeChoiceVo();
        hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
        facultyCodes = hkuProgrammeService.findFacultyCd();
        programmeChoiceVos = new ArrayList<ProgrammeChoiceVo>();
        programmeChoiceVo.setApplicationStatus(Constants.STATUS_SUBMITTED);
        programmeChoiceVo.setEngReqStr("true");
        programmeChoiceVo.setMeetReqStr("true");
        lazyDataModel = new LazyShortlistingDataModel(programmeChoiceVo);
        predictedActual = "A";
        facultyCode = "";
        opQualifications = refCdService.findListByType(Constants.OP_QUALIFICATION);
        offerStatus = refCdService.findListByType(Constants.CHOICESTATUS);
    }

    public void search() {
        if (validateShortlisting()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm aa";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.ENGLISH);
        searchDate = sdf.format(date);
        HkuProgrammeVo hkuProgrammeVo = hkuProgrammeService.get(programmeChoiceVo.getHkuProgrammeId());
        hkuProgrammeDesc = hkuProgrammeVo.getHkuProgrammeDesc();
        status = true;
        lazyDataModel = new LazyShortlistingDataModel(programmeChoiceVo);
    }

    public void reset() {
        LOG.debug("ShortlistingBean reset...");
        status = false;
        init();
    }

    public void searchProgrammes(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String facultyCode = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(facultyCode)) {
            hkuProgrammeVos = hkuProgrammeService.findByFacultyCd(facultyCode);
        } else {
            hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
            programmeChoiceVo.setHkuProgrammeId("");
        }
    }

    public void updateFacultyCode() {
        if (programmeChoiceVos.size() > 0) {
            for (ProgrammeChoiceVo vo : programmeChoiceVos) {
                vo.setOfferStatusCd(Constants.CHOICE_SHORTLISTED);
            }
            programmeChoiceService.save(programmeChoiceVos);
        }
    }

    public void updateProgrammeChoiceStatus(String status) {
        if (CollectionUtils.isNotEmpty(programmeChoiceVos)) {
            for (ProgrammeChoiceVo vo : programmeChoiceVos) {
                if (Constants.READY_FOR_OFFER_CODE.equals(status))
                    vo.setOfferStatusCd(Constants.READY_FOR_OFFER_CODE);
                else if (Constants.CHOICE_WAITLIST.equals(status))
                    vo.setOfferStatusCd(Constants.CHOICE_WAITLIST);
                else if (Constants.CHOICE_REJECTED.equals(status))
                    vo.setOfferStatusCd(Constants.CHOICE_REJECTED);
                else if (Constants.CHOICE_SELECTED_FOR_INTERVIEW.equals(status)) {
                    vo.setSelectForInterview(true);
                    vo.setOfferStatusCd(Constants.CHOICE_SELECTED_FOR_INTERVIEW);
                    disableSelect = true;
                    disableDeselect = false;
                } else if (Constants.CHOICE_SHORTLISTED.equals(status)) {
                    vo.setSelectForInterview(false);
                    vo.setOfferStatusCd(Constants.CHOICE_SHORTLISTED);
                    disableDeselect = true;
                    disableSelect = false;
                }
            }
            programmeChoiceService.save(programmeChoiceVos);
            rowSelectCheckbox();
            UIUtil.update("shortlistingFormPage", false);
        } else
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_RECORD_SELECTED,
                    Constants.NO_RECORD_SELECTED_CHI);
    }

    public void reCalculateScore() {
        if (CollectionUtils.isNotEmpty(programmeChoiceVos)) {
            for (ProgrammeChoiceVo vo : programmeChoiceVos) {
                if (selectedQualifications.contains("SAT")) {

                }
                if (selectedQualifications.contains("GCE")) {
                    recalculateGceActualScore(vo);
                    recalculateGcePredictedAndActualScore(vo);
                }
                if (selectedQualifications.contains("IB42")) {
                    recalculateIbdActualScore42(vo);
                    recalculateIbdPredictedAndActualScore42(vo);
                }
                if (selectedQualifications.contains("IB45")) {
                    recalculateIbdActualScore45(vo);
                    recalculateIbdPredictedAndActualScore45(vo);
                }
            }
        } else
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_RECORD_SELECTED,
                    Constants.NO_RECORD_SELECTED_CHI);
    }

    private void recalculateIbdActualScore42(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("IBD");
        List<IbdBestExamVo> ibdBestExams = new ArrayList<IbdBestExamVo>();
        List<QualificationVo> qualifications = qualificationService
                .getByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (QualificationVo qualification : qualifications) {
            if ("N - No grade".equals(qualification.getIbAchievedRsltGradeCd())) {
                IbdBestExamVo ibdBestExam = new IbdBestExamVo();
                ibdBestExam.setApplicationId(vo.getApplicationId());
                ibdBestExam.setQualificationId(qualification.getId());
                ibdBestExam.setCalculateType("Actual");
                ibdBestExam.setOutOf("42");
                int totalRslt = 0;
                List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                        .getByAppQualificationId(qualification.getId());
                for (QualificationRsltVo qualificationRslt : qualificationRslts) {
                    if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeCd())) {
                        GradeVo grade = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getAchievedGradeCd());
                        totalRslt += grade.getComparableValue();
                    }
                }
                ibdBestExam.setTotalRslt(totalRslt);
                ibdBestExams.add(ibdBestExam);
            }
        }
        Collections.sort(ibdBestExams);
        IbdBestExamVo deleteVo = ibdBestExamService.getBestIBD(vo.getApplicationId(), "Actual", "42");
        if (StringUtils.isNotBlank(deleteVo.getId()))
            ibdBestExamService.delete(deleteVo.getId());
        if (CollectionUtils.isNotEmpty(ibdBestExams))
            ibdBestExamService.save(ibdBestExams.get(0));
    }

    private void recalculateIbdActualScore45(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("IBD");
        List<IbdBestExamVo> ibdBestExams = new ArrayList<IbdBestExamVo>();
        List<QualificationVo> qualifications = qualificationService
                .getByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (QualificationVo qualification : qualifications) {
            if (qualification.getIbAchievedRsltGradeCd() != null
                    && !"N - No grade".equals(qualification.getIbAchievedRsltGradeCd())) {
                IbdBestExamVo ibdBestExam = new IbdBestExamVo();
                ibdBestExam.setApplicationId(vo.getApplicationId());
                ibdBestExam.setQualificationId(qualification.getId());
                ibdBestExam.setCalculateType("Actual");
                ibdBestExam.setOutOf("45");
                Integer totalRslt = 0;
                List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                        .getByAppQualificationId(qualification.getId());
                for (QualificationRsltVo qualificationRslt : qualificationRslts) {
                    if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeCd())) {
                        GradeVo grade = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getAchievedGradeCd());
                        totalRslt += grade.getComparableValue();
                    }
                }
                if (qualification.getIbAchievedRsltGradeCd() != null
                        && !"N - No grade".equals(qualification.getIbAchievedRsltGradeCd()))
                    totalRslt += Integer.parseInt(qualification.getIbAchievedRsltGradeCd());
                ibdBestExam.setTotalRslt(totalRslt);
                ibdBestExams.add(ibdBestExam);
            }
        }
        Collections.sort(ibdBestExams);
        IbdBestExamVo deleteVo = ibdBestExamService.getBestIBD(vo.getApplicationId(), "Actual", "45");
        if (StringUtils.isNotBlank(deleteVo.getId()))
            ibdBestExamService.delete(deleteVo.getId());
        if (CollectionUtils.isNotEmpty(ibdBestExams))
            ibdBestExamService.save(ibdBestExams.get(0));
    }

    private void recalculateIbdPredictedAndActualScore42(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("IBD");
        List<IbdBestExamVo> ibdBestExams = new ArrayList<IbdBestExamVo>();
        List<QualificationVo> qualifications = qualificationService
                .getByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (QualificationVo qualification : qualifications) {
            if (("N - No grade".equals(qualification.getIbAchievedRsltGradeCd())
                    || "N - No grade".equals(qualification.getIbPredictedRsltGradeCd()))
                    && !(qualification.getIbAchievedRsltGradeCd() != null
                            && !"N - No grade".equals(qualification.getIbAchievedRsltGradeCd()))
                    && !(qualification.getIbPredictedRsltGradeCd() != null
                            && !"N - No grade".equals(qualification.getIbPredictedRsltGradeCd()))) {
                IbdBestExamVo ibdBestExam = new IbdBestExamVo();
                ibdBestExam.setApplicationId(vo.getApplicationId());
                ibdBestExam.setQualificationId(qualification.getId());
                ibdBestExam.setCalculateType("Predicted And Actual");
                ibdBestExam.setOutOf("42");
                int totalRslt = 0;
                List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                        .getByAppQualificationId(qualification.getId());
                for (QualificationRsltVo qualificationRslt : qualificationRslts) {
                    GradeVo gradeAchieved = null;
                    GradeVo gradePredicted = null;
                    if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeCd())) {
                        gradeAchieved = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getAchievedGradeCd());
                    }
                    if (StringUtils.isNotBlank(qualificationRslt.getPredictedGradeCd())) {
                        gradePredicted = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getPredictedGradeCd());
                    }
                    totalRslt += gradeAchieved == null ? gradePredicted.getComparableValue()
                            : gradeAchieved.getComparableValue();
                }
                ibdBestExam.setTotalRslt(totalRslt);
                ibdBestExams.add(ibdBestExam);
            }
        }
        Collections.sort(ibdBestExams);
        IbdBestExamVo deleteVo = ibdBestExamService.getBestIBD(vo.getApplicationId(), "Predicted and Actual", "42");
        if (StringUtils.isNotBlank(deleteVo.getId()))
            ibdBestExamService.delete(deleteVo.getId());
        if (CollectionUtils.isNotEmpty(ibdBestExams))
            ibdBestExamService.save(ibdBestExams.get(0));
    }

    private void recalculateIbdPredictedAndActualScore45(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("IBD");
        List<IbdBestExamVo> ibdBestExams = new ArrayList<IbdBestExamVo>();
        List<QualificationVo> qualifications = qualificationService
                .getByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (QualificationVo qualification : qualifications) {
            if ((qualification.getIbPredictedRsltGradeCd() != null
                    && !"N - No grade".equals(qualification.getIbAchievedRsltGradeCd()))
                    || (qualification.getIbAchievedRsltGradeCd() != null
                            && !"N - No grade".equals(qualification.getIbPredictedRsltGradeCd()))) {
                IbdBestExamVo ibdBestExam = new IbdBestExamVo();
                ibdBestExam.setApplicationId(vo.getApplicationId());
                ibdBestExam.setQualificationId(qualification.getId());
                ibdBestExam.setCalculateType("Predicted And Actual");
                ibdBestExam.setOutOf("45");
                int totalRslt = 0;
                List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                        .getByAppQualificationId(qualification.getId());
                for (QualificationRsltVo qualificationRslt : qualificationRslts) {
                    GradeVo gradeAchieved = null;
                    GradeVo gradePredicted = null;
                    if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeCd())) {
                        gradeAchieved = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getAchievedGradeCd());
                    }
                    if (StringUtils.isNotBlank(qualificationRslt.getPredictedGradeCd())) {
                        gradePredicted = gradeService.getByExamTypeIdAndGradeCd(type.getId(),
                                qualificationRslt.getPredictedGradeCd());
                    }
                    totalRslt += gradeAchieved == null ? gradePredicted.getComparableValue()
                            : gradeAchieved.getComparableValue();
                }
                Integer achieved = null;
                Integer predicted = null;
                if (qualification.getIbAchievedRsltGradeCd() != null
                        && !"N - No grade".equals(qualification.getIbAchievedRsltGradeCd()))
                    achieved = Integer.parseInt(qualification.getIbAchievedRsltGradeCd());
                if (qualification.getIbPredictedRsltGradeCd() != null
                        && !"N - No grade".equals(qualification.getIbPredictedRsltGradeCd()))
                    predicted = Integer.parseInt(qualification.getIbPredictedRsltGradeCd());
                if (achieved != null && predicted != null) {
                    if (predicted > achieved)
                        totalRslt += predicted;
                    else
                        totalRslt += achieved;
                } else if (achieved != null)
                    totalRslt += achieved;
                else if (predicted != null)
                    totalRslt += predicted;
                ibdBestExam.setTotalRslt(totalRslt);
                ibdBestExams.add(ibdBestExam);
            }
        }
        Collections.sort(ibdBestExams);
        IbdBestExamVo deleteVo = ibdBestExamService.getBestIBD(vo.getApplicationId(), "Predicted and Actual", "45");
        if (StringUtils.isNotBlank(deleteVo.getId()))
            ibdBestExamService.delete(deleteVo.getId());
        if (CollectionUtils.isNotEmpty(ibdBestExams))
            ibdBestExamService.save(ibdBestExams.get(0));
    }

    private void recalculateGceActualScore(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("GCE");

        BestExamSubjVo bestExamSubj = bestExamSubjService
                .getByApplicationIdAndExamTypeIdAndCalculateType(vo.getApplicationId(), type.getId(), "Actual");
        List<BestExamSubjRsltVo> bestExamSubjRslts = null;
        if (bestExamSubj != null) {
            bestExamSubjRslts = bestExamSubjRsltService.findByBestExamSubjId(bestExamSubj.getId());
            for (BestExamSubjRsltVo bestExamSubjRslt : bestExamSubjRslts) {
                bestExamSubjRsltService.delete(bestExamSubjRslt.getId());
            }
            bestExamSubjService.delete(bestExamSubj.getId());
        }

        bestExamSubj = new BestExamSubjVo();
        bestExamSubj.setApplicationId(vo.getApplicationId());
        bestExamSubj.setExamTypeId(type.getId());
        bestExamSubj.setCalculateType("Actual");
        bestExamSubj = bestExamSubjService.save(bestExamSubj);
        bestExamSubjRslts = new ArrayList<BestExamSubjRsltVo>();
        List<String> subjectIds = qualificationRsltService
                .getExamSubjectIdsByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (String subjectId : subjectIds) {
            BestExamSubjRsltVo bestExamSubjRslt = qualificationRsltService
                    .getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(vo.getApplicationId(),
                            type.getId(), subjectId);
            if (bestExamSubjRslt != null) {
                bestExamSubjRslt.setBestExamSubjId(bestExamSubj.getId());
                bestExamSubjRslts.add(bestExamSubjRslt);
            }
        }
        Collections.sort(bestExamSubjRslts);
        if (bestExamSubjRslts.size() >= 5)
            bestExamSubjRslts = bestExamSubjRslts.subList(0, 5);
        for (BestExamSubjRsltVo bestExamSubjRslt : bestExamSubjRslts) {
            int seqNo = bestExamSubjRslts.indexOf(bestExamSubjRslt) + 1;
            bestExamSubjRslt.setSeqNo(seqNo);
        }
        bestExamSubjRslts = bestExamSubjRsltService.save(bestExamSubjRslts);
    }

    private void recalculateGcePredictedAndActualScore(ProgrammeChoiceVo vo) {
        TypeVo type = typeService.getByExamCd("GCE");

        BestExamSubjVo bestExamSubj = bestExamSubjService.getByApplicationIdAndExamTypeIdAndCalculateType(
                vo.getApplicationId(), type.getId(), "Predicted And Actual");
        List<BestExamSubjRsltVo> bestExamSubjRslts = null;
        if (bestExamSubj != null) {
            bestExamSubjRslts = bestExamSubjRsltService.findByBestExamSubjId(bestExamSubj.getId());
            for (BestExamSubjRsltVo bestExamSubjRslt : bestExamSubjRslts) {
                bestExamSubjRsltService.delete(bestExamSubjRslt.getId());
            }
            bestExamSubjService.delete(bestExamSubj.getId());
        }

        bestExamSubj = new BestExamSubjVo();
        bestExamSubj.setApplicationId(vo.getApplicationId());
        bestExamSubj.setExamTypeId(type.getId());
        bestExamSubj.setCalculateType("Predicted And Actual");
        bestExamSubj = bestExamSubjService.save(bestExamSubj);
        bestExamSubjRslts = new ArrayList<BestExamSubjRsltVo>();
        List<String> subjectIds = qualificationRsltService
                .getExamSubjectIdsByApplicationIdAndExamTypeId(vo.getApplicationId(), type.getId());
        for (String subjectId : subjectIds) {
            List<BestExamSubjRsltVo> subjRsltVos = qualificationRsltService
                    .getBestGceSubjsPredictedAndActualScores(vo.getApplicationId(), type.getId(), subjectId);
            for (BestExamSubjRsltVo subjRsltVo : subjRsltVos) {
                GradeVo actualGrade = StringUtils.isBlank(subjRsltVo.getExamGradeCd()) ? null
                        : gradeService.getByExamTypeIdAndGradeCdAndExamLevel(type.getId(), subjRsltVo.getExamGradeCd(),
                                subjRsltVo.getExamLevel());
                GradeVo predictedGrade = StringUtils.isBlank(subjRsltVo.getPredictedGradeCd()) ? null
                        : gradeService.getByExamTypeIdAndGradeCdAndExamLevel(type.getId(),
                                subjRsltVo.getPredictedGradeCd(), subjRsltVo.getExamLevel());
                if (actualGrade != null && predictedGrade != null
                        && predictedGrade.getComparableValue() > actualGrade.getComparableValue()) {
                    subjRsltVo.setExamGradeCd(subjRsltVo.getPredictedGradeCd());
                    subjRsltVo.setComparableValue(predictedGrade.getComparableValue());
                    subjRsltVo.setRsltType("Predicted");
                } else if (actualGrade == null && predictedGrade != null) {
                    subjRsltVo.setExamGradeCd(subjRsltVo.getPredictedGradeCd());
                    subjRsltVo.setComparableValue(predictedGrade.getComparableValue());
                    subjRsltVo.setRsltType("Predicted");
                }
            }
            Collections.sort(subjRsltVos);
            if (CollectionUtils.isNotEmpty(subjRsltVos))
                bestExamSubjRslts.add(subjRsltVos.get(0));
        }
        Collections.sort(bestExamSubjRslts);
        if (bestExamSubjRslts.size() >= 5)
            bestExamSubjRslts = bestExamSubjRslts.subList(0, 5);
        for (BestExamSubjRsltVo bestExamSubjRslt : bestExamSubjRslts) {
            int seqNo = bestExamSubjRslts.indexOf(bestExamSubjRslt) + 1;
            bestExamSubjRslt.setSeqNo(seqNo);
            bestExamSubjRslt.setBestExamSubjId(bestExamSubj.getId());
        }
        bestExamSubjRslts = bestExamSubjRsltService.save(bestExamSubjRslts);
    }

    public void rowSelectCheckbox() {
        disableDeselect = false;
        disableSelect = false;
        if (CollectionUtils.isNotEmpty(programmeChoiceVos)) {
            for (ProgrammeChoiceVo vo : programmeChoiceVos) {
                if (!Constants.CHOICE_SELECTED_FOR_INTERVIEW.equals(vo.getOfferStatusCd())
                        || !vo.getSelectForInterview())
                    disableDeselect = true;
                if (!Constants.CHOICE_SUBMITTED.equals(vo.getOfferStatusCd())
                        && !Constants.CHOICE_SHORTLISTED.equals(vo.getOfferStatusCd()))
                    disableSelect = true;
            }
        }
    }

    public void removeFromShortlist(String id) {
        ProgrammeChoiceVo programmeChoiceVo = programmeChoiceService.get(id);
        programmeChoiceVo.setOfferStatusCd(Constants.CHOICE_SUBMITTED);
        programmeChoiceService.save(programmeChoiceVo);
    }

    public void shortlist(String id) {
        ProgrammeChoiceVo programmeChoiceVo = programmeChoiceService.get(id);
        programmeChoiceVo.setOfferStatusCd(Constants.CHOICE_SHORTLISTED);
        programmeChoiceService.save(programmeChoiceVo);
    }

    private boolean validateShortlisting() {
        boolean value = false;
        if (StringUtils.isEmpty(facultyCode)) {
            UIUtil.setInvalid("shortlistingForm:faculty");
            value = true;
        }
        if (StringUtils.isEmpty(programmeChoiceVo.getHkuProgrammeId())) {
            UIUtil.setInvalid("shortlistingForm:programme");
            value = true;
        }
        return value;
    }

    public String formatMonthNumToStr(String monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = null;
        try {
            date = sdf.parse(monthNum);
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }
        sdf = new SimpleDateFormat("MMM", Locale.US);
        return sdf.format(date);

    }

    public ProgrammeChoiceVo getProgrammeChoiceVo() {
        return programmeChoiceVo;
    }

    public void setProgrammeChoiceVo(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }

    public LazyDataModel<ProgrammeChoiceVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ProgrammeChoiceVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<String> getFacultyCodes() {
        return facultyCodes;
    }

    public void setFacultyCodes(List<String> facultyCodes) {
        this.facultyCodes = facultyCodes;
    }

    public List<HkuProgrammeVo> getHkuProgrammeVos() {
        return hkuProgrammeVos;
    }

    public void setHkuProgrammeVos(List<HkuProgrammeVo> hkuProgrammeVos) {
        this.hkuProgrammeVos = hkuProgrammeVos;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public List<ProgrammeChoiceVo> getProgrammeChoiceVos() {
        return programmeChoiceVos;
    }

    public void setProgrammeChoiceVos(List<ProgrammeChoiceVo> programmeChoiceVos) {
        this.programmeChoiceVos = programmeChoiceVos;
    }

    public String getHkuProgrammeDesc() {
        return hkuProgrammeDesc;
    }

    public void setHkuProgrammeDesc(String hkuProgrammeDesc) {
        this.hkuProgrammeDesc = hkuProgrammeDesc;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public boolean isDisableDeselect() {
        return disableDeselect;
    }

    public boolean isDisableSelect() {
        return disableSelect;
    }

    public List<String> getSelectedQualifications() {
        return selectedQualifications;
    }

    public void setSelectedQualifications(List<String> selectedQualifications) {
        this.selectedQualifications = selectedQualifications;
    }

    public List<RefCdVo> getOpQualifications() {
        return opQualifications;
    }

    public String getPredictedActual() {
        return predictedActual;
    }

    public void setPredictedActual(String predictedActual) {
        this.predictedActual = predictedActual;
    }

    public List<RefCdVo> getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(List<RefCdVo> offerStatus) {
        this.offerStatus = offerStatus;
    }
}
