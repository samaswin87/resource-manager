package com.company.resource.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TeamTree implements Comparable<TeamTree> {
	
	private String id;
	private String text;
	private String icon;
	private String[] tags;
	private String href;
	@JsonIgnore
	private String code;
	private List<TeamTree> nodes;
	
	public TeamTree() {
		this.setHref("#");
		this.setIcon("fa fa-users fa_team");
		this.setTags(new String[] {"0"});
	}
	
	@Override
	public int compareTo(TeamTree tree) {
		return tree.getCode().compareTo(this.getCode());
	}
}