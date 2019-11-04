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
@Table(name="team_mappings")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NamedQuery(name="TeamMapping.findAll", query="SELECT t FROM TeamMapping t")
@ToString(exclude = {"team", "parentTeam"})
@AllArgsConstructor
@NoArgsConstructor
public class TeamMapping extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="no_of_employees", columnDefinition = "integer default 0")
	private Integer noOfEmployees;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id", nullable=false)
	private Team team;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_team_id", nullable=false)
	private Team parentTeam;

}