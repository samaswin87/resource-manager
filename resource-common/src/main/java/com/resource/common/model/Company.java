package com.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="companies")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(exclude = {
		"companies", "employees", "parent", "employeeTypes", 
		"shiftTypes", "teams"
		})
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private byte active;

	@NotEmpty(message = "{company.email}")
	private String email;

	@NotEmpty(message = "{company.name}")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trail_started_on")
	private Date trailStartedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trail_ended_on")
	private Date trailEndedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Company parent;

	@OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
	private List<Company> companies;

	@OneToMany(mappedBy="company", cascade = CascadeType.ALL)
	private List<Employee> employees;

	@OneToMany(mappedBy="company")
	private List<EmployeeType> employeeTypes;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.ALL)
	private List<ShiftType> shiftTypes;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.ALL)
	private List<Team> teams;
	
	public Company(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getStatus() {
		return this.getActive() == 1 ? "Active" : "In Active";
	}
}