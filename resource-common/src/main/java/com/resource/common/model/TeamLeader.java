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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="team_leaders")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NamedQuery(name="TeamLeader.findAll", query="SELECT t FROM TeamLeader t")
@ToString(exclude = {"team", "employee"})
@AllArgsConstructor
@NoArgsConstructor
public class TeamLeader extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="approvals_effective_date")
	private Date approvalsEffectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="is_approver")
	private Boolean isApprover;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable=false)
	private Date startDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id", nullable=false)
	private Team team;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	public String displayEndDate() {
		return this.getEndDate() == null ? "Not set" : this.getEndDate().toString();
	}
	
	public String displayName() {
		return this.employee.getName();
	}
}