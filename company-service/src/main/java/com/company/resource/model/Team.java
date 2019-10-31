package com.company.resource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	
	private String text;
	private String icon;
	private String[] tags;
	private String href;
	private Boolean state;
	private Team[] nodes;

}
