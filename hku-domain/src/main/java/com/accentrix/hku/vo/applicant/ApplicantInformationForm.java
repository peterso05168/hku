package com.accentrix.hku.vo.applicant;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.domain.Pageable;

import com.accentrix.hku.jaxb.PageableAdapter;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月28日 上午11:23:25 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApplicantInformationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private ApplicantInformationVo applicantInformationVo;
    private Pageable pageable;

    public ApplicantInformationForm() {

    }

    public ApplicantInformationForm(ApplicantInformationVo applicantInformationVo, Pageable pageable) {
        this.applicantInformationVo = applicantInformationVo;
        this.pageable = pageable;
    }

    @XmlJavaTypeAdapter(PageableAdapter.class)
    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }
}
