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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name="team_members")
@NamedQuery(name="TeamMember.findAll", query="SELECT t FROM TeamMember t")
@ToString(exclude = {"team", "employee"})
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private Boolean active;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="is_approver")
	private Boolean isApprover;

	@Column(name="is_leader")
	private Boolean isLeader;

	@Column(name="is_primary")
	private Boolean isPrimary;

	@NotNull(message="Start date must not be empty")
	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable=false)
	private Date startDate;

	@NotNull(message="Title must not be empty")
	@NotEmpty(message="Title must not be empty")
	@Column(length=120)
	private String title;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id", nullable=false)
	private Team team;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	public String displayName() {
		return this.employee.getName();
	}
	
	public String displayEndDate() {
		return this.getEndDate() == null ? "Not set" : this.getEndDate().toString();
	}
}