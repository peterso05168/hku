package com.accentrix.hku.domain.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@ApiObject(name = "AuditedObject", show = false)
public class AuditedObject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @CreatedBy
    @Column(name = "create_by", length = 32)
    @ApiObjectField(description = "The user id who created this record")
    protected String createBy;

    /**
     * 创建日期
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    @ApiObjectField(description = "The record creation date")
    protected Date createDate;

    /**
     * 更新者
     */
    @LastModifiedBy
    @Column(name = "update_by", length = 32)
    @ApiObjectField(description = "The user id who last updated this record")
    protected String updateBy;

    /**
     * 更新日期
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    @ApiObjectField(description = "The record modified date")
    protected Date updateDate;

    /**
     * 数据版本号（更新或删除数据时使用乐观锁机制）
     */
    @Version
    @Column(name = "version")
    @ApiObjectField(description = "The optimistic lock value")
    protected int version;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
