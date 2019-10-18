package com.resource.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the employee_types database table.
 *
 */
@Entity
@Table(name="employee_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"company", "employmentRelationships"})
@NamedQuery(name="EmployeeType.findAll", query="SELECT e FROM EmployeeType e")
public class EmployeeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String code;

	@Column(nullable=false, length=45)
	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=false)
	private Company company;

	@OneToMany(mappedBy="employeeType")
	private List<EmploymentRelationship> employmentRelationships;

}