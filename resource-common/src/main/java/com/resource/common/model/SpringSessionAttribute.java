package com.resource.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * org.hibernate.MappingException: Composite-id class must implement Serializable
 * attribute_name and session_primary_id are composite key
 */
public class SpringSessionAttribute implements Serializable{

	private static final long serialVersionUID = 1730845780563385602L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Lob
	@Column(name="ATTRIBUTE_BYTES")
	private byte[] attributeBytes;

	@Column(name="ATTRIBUTE_NAME")
	private String attributeName;

	@Column(name="SESSION_PRIMARY_ID")
	private String sessionPrimaryId;
	
}
