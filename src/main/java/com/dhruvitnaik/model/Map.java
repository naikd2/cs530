package com.dhruvitnaik.model;

import com.dhruvitnaik.util.IdGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Region(value = "Map")
public class Map implements Serializable {

	@Id
	private Long id;

	private Double latitude;

	private Double longitude;

	private List<Long> pins = new ArrayList<>(5);

	public Map() {
		this.id = IdGenerator.INSTANCE.getNextMapId();
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

	public List<Long> getPins() {
		return pins;
	}

	public void setPins(List<Long> pins) {
		this.pins = pins;
	}
}
