package com.accentrix.hku.web.common;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.vo.general.RefCdVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年2月9日 下午5:41:33
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RefCdBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RefCdBean.class);

    @Autowired
    private RefCdService refCdService;

    public RefCdBean() {
        LOG.info("init...");
    }

    public String findRefCdValueByTypeAndCode(String type, String code) {
        if (StringUtils.isNotBlank(code)) {
            RefCdVo vo = refCdService.getByTypeAndCd(type, code);
            return vo.getValue();
        }
        return "";
    }
}
