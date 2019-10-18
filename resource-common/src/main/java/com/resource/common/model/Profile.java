package com.resource.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.http.MediaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the profiles database table.
 * 
 */
@Entity
@Table(name="profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String filename;
	
	@Column(name="content_type", nullable=false, length=45)
	private String contentType;

	@Lob
	@Column(length=100000)
	private byte[] image;

	@ToString.Exclude
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	public MediaType getType() {
		MediaType media = MediaType.IMAGE_GIF;
		switch(this.contentType) {
		case("gif"):
			media = MediaType.IMAGE_GIF;
			break;
		case("jpeg"):
			media = MediaType.IMAGE_JPEG;
			break;
		case("png"):
			media = MediaType.IMAGE_PNG;
			break;
		}
		return media;
	}

}