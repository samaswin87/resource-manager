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

import com.resource.common.constants.AddressType;
import com.resource.common.constants.City;
import com.resource.common.constants.Country;
import com.resource.common.constants.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the addresses database table.
 *
 */
@Entity
@Table(name="addresses")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	private String details;

	@Column(name="address_type", nullable=false)
	private Integer addressType;

	private int city;

	private int country;

	private boolean current;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on")
	private Date endedOn;

	@Column(name="postal_code", nullable=false, length=15)
	private String postalCode;

	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;

	private int state;

	@Column(name="status_id", nullable=false)
	private Integer statusId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	@Column(name="other_country")
	private String otherCountry;
	
	@Column(name="other_state")
	private String otherState;
	
	@Column(name="other_city")
	private String otherCity;
	
	public String getAddressTypeName() {
		int addressType = this.getAddressType() == null ? 0 : this.getAddressType().intValue();
		return AddressType.getAddressType(addressType).getName();
	}

	public String getCountryName() {
		return Country.getCountry(this.country).getName();
	}
	
	public String getStateName() {
		return State.getState(this.state).getName();
	}
	
	public String getCityName() {
		return City.getCity(this.city).getName();
	}
}