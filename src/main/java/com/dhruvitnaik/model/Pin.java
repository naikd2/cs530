package com.dhruvitnaik.model;

import com.dhruvitnaik.util.IdGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

@Region(value = "Pin")
public class Pin implements Serializable {

	@Id
	private Long id;

	private Double latitude;

	private Double longitude;


	/*

	Person Info
	Age
	Sex
	Alias
	Symptoms
	Location Info
	Name
	Indoor/Outdoor
	Weather
	Climate
	// option for search for location as well as drop


	 */

	public Pin() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
