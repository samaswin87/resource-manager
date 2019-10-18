package com.resource.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"roles", "employee"})
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte enabled;

	private String password;

	private String username;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="user")
	private List<Role> roles;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

}