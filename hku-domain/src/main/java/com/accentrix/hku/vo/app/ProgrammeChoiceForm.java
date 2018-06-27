package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.domain.Pageable;

import com.accentrix.hku.jaxb.PageableAdapter;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月16日 上午11:00:00
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProgrammeChoiceForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProgrammeChoiceVo programmeChoiceVo;
    private Pageable pageable;

    public ProgrammeChoiceForm() {
    }

    public ProgrammeChoiceForm(ProgrammeChoiceVo programmeChoiceVo, Pageable pageable) {
        super();
        this.programmeChoiceVo = programmeChoiceVo;
        this.pageable = pageable;
    }

    public ProgrammeChoiceVo getProgrammeChoiceVo() {
        return programmeChoiceVo;
    }

    public void setProgrammeChoiceVo(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }

    @XmlJavaTypeAdapter(PageableAdapter.class)
    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
