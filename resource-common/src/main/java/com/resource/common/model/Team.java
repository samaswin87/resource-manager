package com.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.resource.common.utils.DateUtil;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="teams")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
@ToString(exclude = {
		"teamLeaders", "teamMembers", "company", "teamMappings", 
		"parentTeamMappings", 
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

	@NotEmpty(message="Team must have code")
	@Column(nullable=false, length=45)
	private String code;

	@Column(length=256)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="ended_on")
	private Date endedOn;

	@NotEmpty(message="Team must have name")
	@Column(nullable=false, length=120)
	private String name;

	@NotNull(message="Started on must not be empty")
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
	
	@OneToMany(mappedBy="parentTeam")
	private List<TeamMapping> parentTeamMappings;

	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)
	private List<TeamMapping> teamMappings;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
            name="team_mappings",
            joinColumns = @JoinColumn( name="parent_team_id"),
            inverseJoinColumns = @JoinColumn( name="team_id")
    )
	private List<TeamMapping> childTeamMappings;
	
	public String getDisplayName() {
		return this.getName() +" (" +this.getCode() +")";
	}
	
	public String displayEndedOn() {
		return this.getEndedOn() == null ? DateUtil.getInstance().today() : this.getEndedOn().toString();
	}
}