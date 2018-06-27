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
 * @date 创建时间：2018年3月30日 上午10:03:38
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoleForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private RoleVo roleVo;
    private Pageable pageable;

    public RoleForm() {
    }

    public RoleForm(RoleVo roleVo, Pageable pageable) {
        super();
        this.roleVo = roleVo;
        this.pageable = pageable;
    }

    public RoleVo getRoleVo() {
        return roleVo;
    }

    public void setRoleVo(RoleVo roleVo) {
        this.roleVo = roleVo;
    }

    @XmlJavaTypeAdapter(PageableAdapter.class)
    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
