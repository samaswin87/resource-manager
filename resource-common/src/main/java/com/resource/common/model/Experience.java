package com.resource.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the experiences database table.
 *
 */
@Entity
@Table(name="experiences")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="Experience.findAll", query="SELECT e FROM Experience e")
public class Experience extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	@Column(name="company_address")
	private String companyAddress;

	@Column(name="company_name", nullable=false, length=45)
	private String companyName;

	@Column(name="company_phone", nullable=false, length=15)
	private String companyPhone;

	@Column(name="company_website", nullable=false, length=45)
	private String companyWebsite;

	@Column(length=45)
	private String department;

	@Column(nullable=false, length=45)
	private String designation;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on", nullable=false)
	private Date endedOn;

	@Column(name="referrer_email", nullable=false, length=45)
	private String referrerEmail;

	@Column(name="referrer_name", nullable=false, length=45)
	private String referrerName;

	@Column(name="referrer_phone", nullable=false, length=15)
	private String referrerPhone;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal salary;

	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;

	@Column(name="status_id", nullable=false)
	private byte statusId;

	@Column(nullable=false, length=45)
	private String supervisor;

	@Column(name="termination_reason", nullable=false, length=45)
	private String terminationReason;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

}