package com.resource.common.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="teams")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
@ToString(exclude = {
		"teamLeaders", "teamMembers", "company", "teams", 
		"parent"
		})
@AllArgsConstructor
@NoArgsConstructor
public class Team extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="admin_only")
	private Boolean adminOnly;

	@Column(nullable=false, length=45)
	private String code;

	@Column(nullable=false)
	private int depth;

	@Column(length=256)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on")
	private Date endedOn;

	@Column(nullable=false, length=120)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="started_on", nullable=false)
	private Date startedOn;

	@OneToMany(mappedBy="team")
	private List<TeamLeader> teamLeaders;

	@OneToMany(mappedBy="team")
	private List<TeamMember> teamMembers;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=false)
	private Company company;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Team parent;

	@OneToMany(mappedBy="parent")
	private List<Team> teams;

}