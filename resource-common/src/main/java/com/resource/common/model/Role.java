package com.resource.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
}