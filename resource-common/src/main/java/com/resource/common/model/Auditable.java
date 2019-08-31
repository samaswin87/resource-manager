package com.resource.common.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Moving necessary fields to super class and providing AuditingEntityListener entity listner class
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"created_at", "updated_at", "created_by", "modified_by"})
public class Auditable<U> {

	@CreatedBy
	@Column(name="created_by", length = 30)
    protected U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    protected Date createdAt;

    @LastModifiedBy
    @Column(name="modified_by", length = 30)
    protected U modifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    protected Date modifiedAt;
}
