package com.accentrix.hku.vo.staff;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.data.domain.Pageable;

import com.accentrix.hku.jaxb.PageableAdapter;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月30日 上午10:18:20
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StaffInformationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private StaffInformationVo staffInformationVo;
    private Pageable pageable;

    public StaffInformationForm() {
    }

    public StaffInformationForm(StaffInformationVo staffInformationVo, Pageable pageable) {
        super();
        this.staffInformationVo = staffInformationVo;
        this.pageable = pageable;
    }

    public StaffInformationVo getStaffInformationVo() {
        return staffInformationVo;
    }

    public void setStaffInformationVo(StaffInformationVo staffInformationVo) {
        this.staffInformationVo = staffInformationVo;
    }

    @XmlJavaTypeAdapter(PageableAdapter.class)
    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
