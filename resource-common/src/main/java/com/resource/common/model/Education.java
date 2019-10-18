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
 * The persistent class for the educations database table.
 *
 */
@Entity
@Table(name="educations")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on", nullable=false)
	private Date endedOn;

	@Column(length=45)
	private String grade;

	@Column(name="institute_name", length=45)
	private String instituteName;

	private int percentage;

	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;

	@Column(name="type_id", nullable=false)
	private byte typeId;

	@Column(name="university_name", nullable=false, length=65)
	private String universityName;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

}