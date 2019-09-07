package com.resource.common.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SPRING_SESSION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringSession implements Serializable {
	
	private static final long serialVersionUID = 4530506559406135849L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CREATION_TIME")
	private BigInteger creationTime;

	@Column(name="EXPIRY_TIME")
	private BigInteger expiryTime;

	@Column(name="LAST_ACCESS_TIME")
	private BigInteger lastAccessTime;

	@Column(name="MAX_INACTIVE_INTERVAL")
	private int maxInactiveInterval;

	@Column(name="PRIMARY_ID")
	private String primaryId;

	@Column(name="PRINCIPAL_NAME")
	private String principalName;

	@Column(name="SESSION_ID")
	private String sessionId;
}
