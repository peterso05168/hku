package com.accentrix.hku.web.management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class OfferApprovalBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(OfferApprovalBean.class);

    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private HkuProgrammeService hkuProgrammeService;

    private List<ProgrammeChoiceVo> programmeChoiceVos;
    private List<RefCdVo> refCdVos;
    private ProgrammeChoiceVo programmeChoiceVo;
    private LazyDataModel<ProgrammeChoiceVo> lazyDataModel;
    private List<String> facultyCodes;
    private List<HkuProgrammeVo> hkuProgrammeVos;
    private boolean status;
    private String facultyCode;
    private String hkuProgrammeDesc;

    public OfferApprovalBean() {
        LOG.debug("OfferApprovalBean init...");
        init();
    }

    private void init() {
        programmeChoiceVo = new ProgrammeChoiceVo();
        programmeChoiceVo.setOfferStatusCd(Constants.OFFER_APPROVED_CODE);
        hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
        facultyCodes = hkuProgrammeService.findFacultyCd();
        programmeChoiceVos = new ArrayList<ProgrammeChoiceVo>();
        refCdVos = refCdService.findListByType(Constants.CHOICESTATUS);
        facultyCode = "";
        lazyDataModel = new LazyShortlistingDataModel(programmeChoiceVo);
    }

    public void search() {
        if (validateShortlisting()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        HkuProgrammeVo hkuProgrammeVo = hkuProgrammeService.get(programmeChoiceVo.getHkuProgrammeId());
        hkuProgrammeDesc = hkuProgrammeVo.getHkuProgrammeDesc();
        status = true;
        lazyDataModel = new LazyShortlistingDataModel(programmeChoiceVo);
    }

    public void reset() {
        LOG.debug("OfferApprovalBean reset...");
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

    /**
     * 生成sis number
     */
    public void multiIssueOffer() {
        if (programmeChoiceVos == null || programmeChoiceVos.size() <= 0) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        generateSisNumber();
    }

    private void generateSisNumber() {
        Properties properties = null;
        try {
            properties = getProperties(ConstantsUtils.SIS_NUMBER_PROPERTIES_URL);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int startNumber = Integer.parseInt(properties.get("start").toString());
        int endNumber = Integer.parseInt(properties.get("end").toString());
        for (ProgrammeChoiceVo programmeChoice : programmeChoiceVos) {
            programmeChoice.setReplyDeadline(getNextDay(new Date()));
            programmeChoice.setOfferStatusCd(Constants.PROG_CHOICE_OFFERED_CD);
            if (programmeChoice.getSisNumber() == null) {
                RefCdVo refCd = refCdService.getByTypeAndCd("SIS_NUMBER", "SIS_NUMBER");
                Integer sisNumber = Integer.parseInt(refCd.getValue());
                if (sisNumber >= startNumber && sisNumber <= endNumber) {
                    refCd.setValue(new Integer(sisNumber + 1).toString());
                    refCd = refCdService.save(refCd);
                    sisNumber = Integer.parseInt(refCd.getValue());
                    programmeChoice.setSisNumber(sisNumber);
                }
            }
            programmeChoiceService.save(programmeChoice);
        }
    }

    private static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +14);// 时间加14天
        date = calendar.getTime();
        return date;
    }

    public void changeStatus() {
        if (programmeChoiceVos == null || programmeChoiceVos.size() <= 0) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        for (ProgrammeChoiceVo programmeChoice : programmeChoiceVos) {
            programmeChoice.setOfferStatusCd(Constants.CONFIRM_OFFER_APPROVED_CODE);
            programmeChoiceService.save(programmeChoice);
        }
    }

    private Properties getProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        properties.load(bufferedReader);
        return properties;
    }

    private boolean validateShortlisting() {
        boolean value = false;
        if (StringUtils.isEmpty(facultyCode)) {
            UIUtil.setInvalid("appApprovalForm:faculty");
            value = true;
        }
        if (StringUtils.isEmpty(programmeChoiceVo.getHkuProgrammeId())) {
            UIUtil.setInvalid("appApprovalForm:programme");
            value = true;
        }
        return value;
    }

    public List<ProgrammeChoiceVo> getProgrammeChoiceVos() {
        return programmeChoiceVos;
    }

    public void setProgrammeChoiceVos(List<ProgrammeChoiceVo> programmeChoiceVos) {
        this.programmeChoiceVos = programmeChoiceVos;
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

    public String getHkuProgrammeDesc() {
        return hkuProgrammeDesc;
    }

    public void setHkuProgrammeDesc(String hkuProgrammeDesc) {
        this.hkuProgrammeDesc = hkuProgrammeDesc;
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

    public List<RefCdVo> getRefCdVos() {
        return refCdVos;
    }

    public void setRefCdVos(List<RefCdVo> refCdVos) {
        this.refCdVos = refCdVos;
    }

}
