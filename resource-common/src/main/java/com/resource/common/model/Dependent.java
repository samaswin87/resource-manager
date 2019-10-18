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

import com.resource.common.constants.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the dependents database table.
 *
 */
@Entity
@Table(name="dependents")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="Dependent.findAll", query="SELECT d FROM Dependent d")
public class Dependent extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date", nullable=false)
	private Date birthDate;

	@Column(length=45)
	private String city;

	@Column(length=45)
	private String country;

	@Column(name="disability_status", nullable=false)
	private byte disabilityStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(nullable=false)
	private byte gender;

	@Column(precision=10, scale=2)
	private BigDecimal income;

	@Column(name="income_notes", length=45)
	private String incomeNotes;

	@Column(name="income_source", length=45)
	private String incomeSource;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(length=45)
	private String notes;

	@Column(name="occupation_name", length=45)
	private String occupationName;

	@Column(name="occupation_other", length=45)
	private String occupationOther;

	@Column(name="phone_number", length=45)
	private String phoneNumber;

	@Column(name="postal_code", nullable=false)
	private int postalCode;

	@Column(name="relation_type_id", nullable=false)
	private Byte relationTypeId;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable=false)
	private Date startDate;

	@Column(name="status_id", nullable=false)
	private byte statusId;

	@Column(name="tax_deductible", nullable=false)
	private byte taxDeductible;

	@Column(name="taxable_income", length=45)
	private String taxableIncome;

	@Temporal(TemporalType.DATE)
	@Column(name="visa_expiry")
	private Date visaExpiry;

	@Column(name="visa_number", length=45)
	private String visaNumber;

	@Column(name="visa_type_id")
	private byte visaTypeId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	private boolean current;
	
	public String getRelationshipName() {
		return RelationshipType.getRelationship(this.relationTypeId.intValue()).getName();
	}

}