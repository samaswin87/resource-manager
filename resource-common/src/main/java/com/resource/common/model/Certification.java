package com.resource.common.model;

import java.io.Serializable;
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
 * The persistent class for the certifications database table.
 *
 */
@Entity
@Table(name="certifications")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="Certification.findAll", query="SELECT c FROM Certification c")
public class Certification extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="certificate_by", length=45)
	private String certificateBy;

	@Temporal(TemporalType.DATE)
	@Column(name="expire_date")
	private Date expireDate;

	@Temporal(TemporalType.DATE)
	@Column(name="issued_date", nullable=false)
	private Date issuedDate;

	@Column(name="level_id", nullable=false)
	private byte levelId;

	@Column(nullable=false, length=45)
	private String name;

	@Lob
	private String notes;

	@Column(name="status_id", nullable=false)
	private byte statusId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

}