package com.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;

import com.resource.common.validation.GroupPersonalInfo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the employees database table.
 *
 */
@Entity
@Table(name="employees")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(exclude = {
		"addresses", "certifications", "dependents", "company",
		"educations", "emergencyContacts", "employmentRelationships",
		"experiences", "personalDetail", "profile", "users"
})
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@NotEmpty(message = "{employee.email}", groups = GroupPersonalInfo.class)
	@Email
	private String email;

	@NotEmpty(message = "{employee.employeeCode}")
	@Column(name="employee_code")
	private String employeeCode;

	@NotEmpty(message = "{employee.firstName}", groups = GroupPersonalInfo.class)
	@Column(name="first_name")
	private String firstName;

	private boolean gender;

	@NotEmpty(message = "{employee.lastName}", groups = GroupPersonalInfo.class)
	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ended_on", nullable=true)
	private Date endedOn;

	@OneToMany(mappedBy="employee")
	private List<Address> addresses;

	@OneToMany(mappedBy="employee")
	private List<Certification> certifications;

	@OneToMany(mappedBy="employee")
	private List<Dependent> dependents;

	@OneToMany(mappedBy="employee")
	private List<Education> educations;

	@OneToMany(mappedBy="employee")
	private List<EmergencyContact> emergencyContacts;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=false)
	private Company company;

	@OneToMany(mappedBy="employee")
	private List<EmploymentRelationship> employmentRelationships;

	@OneToMany(mappedBy="employee")
	private List<Experience> experiences;

	@OneToOne(mappedBy="employee", cascade = CascadeType.ALL)
	private PersonalDetail personalDetail;
	
	@OneToOne(mappedBy="employee")
	private Profile profile;

	@OneToMany(mappedBy="employee")
	private List<User> users;

	public String fullName() {
		return this.firstName +" "+ this.lastName;
	}
	
	public String getName() {
		return StringUtils.capitalize(this.firstName) +" "+ StringUtils.capitalize(this.lastName);
	}
	
	public String getGenderName() {
		return this.gender ? "Male" : "Female";
	}

	public Address getAddress(Integer id) {
		return this.getAddresses().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}
	
	public Address getCurrentAddress() {
		return getAddresses().stream().filter(a -> a.isCurrent()).findFirst().orElse(null);
	}
	
	public List<Address> getAddressHistory() {
		return getAddresses().stream().filter(a -> !a.isCurrent()).collect(Collectors.toList());
	}
	
	public Dependent getDependent(Integer id) {
		return this.getDependents().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}
	
	public EmergencyContact getContact(Integer id) {
		return this.getEmergencyContacts().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}
}