package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.domain.Pageable;

import com.accentrix.hku.jaxb.PageableAdapter;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月10日 下午2:01:49
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CampaignForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pageable pageable;
    private CampaignVo campaignVo;

    public CampaignForm() {
    }

    public CampaignForm(Pageable pageable, CampaignVo campaignVo) {
        this.pageable = pageable;
        this.campaignVo = campaignVo;
    }

    @XmlJavaTypeAdapter(PageableAdapter.class)
    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public CampaignVo getCampaignVo() {
        return campaignVo;
    }

    public void setCampaignVo(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }
}
