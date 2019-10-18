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
 * The persistent class for the `employment_ relationships` database table.
 *
 */
@Entity
@Table(name="employment_relationships")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee", "employeeType"})
@NamedQuery(name="EmploymentRelationship.findAll", query="SELECT e FROM EmploymentRelationship e")
public class EmploymentRelationship extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private byte current;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on")
	private Date endedOn;


	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_type_id", nullable=false)
	private EmployeeType employeeType;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

}