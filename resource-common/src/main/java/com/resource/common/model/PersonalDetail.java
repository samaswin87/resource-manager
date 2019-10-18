package com.resource.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.resource.common.constants.Country;
import com.resource.common.constants.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the personal_details database table.
 *
 */
@Entity
@Table(name="personal_details")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(exclude = {"employee"})
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="PersonalDetail.findAll", query="SELECT p FROM PersonalDetail p")
public class PersonalDetail extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date", nullable=false)
	private Date birthDate;

	@Column(name="display_first_name", nullable=false, length=45)
	private String displayFirstName;

	@Column(name="display_last_name", nullable=false, length=45)
	private String displayLastName;

	@Column(length=45)
	private String email;

	@Column(name="marital_status", nullable=false)
	private Integer maritalStatus;

	@Column(name="mobile_number", length=15)
	private String mobileNumber;

	@Column(name="nationality_id")
	private int nationalityId;

	@Column(name="passport_number", length=45)
	private String passportNumber;

	@Column(name="phone_number", length=15)
	private String phoneNumber;

	@Column(name="status_id", nullable=false)
	private byte statusId;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	public String getMaritalStatusName() {
		return MaritalStatus.getMaritalStatus(this.maritalStatus).getName();
	}
	
	public String getNationality() {
		return Country.getCountry(this.nationalityId).getName();
	}

}