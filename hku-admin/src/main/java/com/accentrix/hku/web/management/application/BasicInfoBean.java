package com.accentrix.hku.web.management.application;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 上午10:57:54 
 * @version 1.0 
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class BasicInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(BasicInfoBean.class);

    @Autowired
    private ApplicationService applicationService;

    private ApplicationVo applicationVo;

    public BasicInfoBean() {
        LOG.debug("BasicInfoBean init...");
        String applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        applicationVo = applicationService.getByApplicationId(applicationId);
    }

    public ApplicationVo getApplicationVo() {
        return applicationVo;
    }

    public void setApplicationVo(ApplicationVo applicationVo) {
        this.applicationVo = applicationVo;
    }
}
