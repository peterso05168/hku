package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.service.adm.AnncmntTemplateService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.AdmAnncmntVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AnnouncementEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AnncmntTemplateService anncmntTemplateService;

    private AdmAnncmntVo anncmntVo;

    public AnnouncementEditBean() {
        init();
    }

    public void init() {
        String anncmntId = (String) JSFUtil.getSessionMap().get("anncmntId");
        if (StringUtils.isNotBlank(anncmntId))
            anncmntVo = anncmntTemplateService.get(anncmntId);
    }

    public void save() {
        if (StringUtils.isNotBlank(anncmntVo.getContent())) {
            anncmntVo.setModifyDate(new Date());
            anncmntTemplateService.save(anncmntVo);
            UIUtil.displaySaveSuccessDialog(ConstantCommon.LOCALE_UK);
        } else {
            UIUtil.setInvalid("announcementDetails:announcementTab:content");
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        }
    }

    public String formatDateMmddyyyy(Date date) {
        return date == null ? "" : DateFormatUtils.format(date, "mm/dd/yyyy", TimeZone.getTimeZone("UTC+8:00"));
    }

    public AdmAnncmntVo getAnncmntVo() {
        return anncmntVo;
    }

    public void setAnncmntVo(AdmAnncmntVo anncmntVo) {
        this.anncmntVo = anncmntVo;
    }

}
