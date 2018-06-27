package com.accentrix.hku.web.management.application;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class SupportingDocumentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ReqDocService reqDocService;

    private List<ReqDocVo> pisReqDocVos;

    private List<ReqDocVo> othersReqDocVos;

    @PostConstruct
    public void init() {
        String applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        if (StringUtils.isNotBlank(applicationId)) {
            pisReqDocVos = reqDocService.findByReqDocType(applicationId, Constants.DOC_TYPE_PIS);
            othersReqDocVos = reqDocService.findByReqDocType(applicationId, Constants.DOC_TYPE_OTHERS);
        }
    }

    public String formatDateYyyyMMdd(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public List<ReqDocVo> getPisReqDocVos() {
        return pisReqDocVos;
    }

    public void setPisReqDocVos(List<ReqDocVo> pisReqDocVos) {
        this.pisReqDocVos = pisReqDocVos;
    }

    public List<ReqDocVo> getOthersReqDocVos() {
        return othersReqDocVos;
    }

    public void setOthersReqDocVos(List<ReqDocVo> othersReqDocVos) {
        this.othersReqDocVos = othersReqDocVos;
    }

}
