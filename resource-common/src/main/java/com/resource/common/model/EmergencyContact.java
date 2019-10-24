package com.resource.common.model;

import java.io.Serializable;

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

import com.resource.common.constants.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the emergency_contacts database table.
 *
 */
@Entity
@Table(name="emergency_contacts")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"employee"})
@NamedQuery(name="EmergencyContact.findAll", query="SELECT e FROM EmergencyContact e")
public class EmergencyContact extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	private String address;

	@Column(length=45)
	private String email;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="mobile_number", nullable=false, length=15)
	private String mobileNumber;

	@Column(nullable=false, length=45)
	private String name;

	@Column(name="phone_number", length=15)
	private String phoneNumber;

	@Column(name="status_id", nullable=false)
	private Integer statusId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	@Column(name="relationship_id", nullable=false)
	private Integer relationshipId;
	
	public String getRelationshipName() {
		return RelationshipType.getRelationship(this.relationshipId).getName();
	}
} 