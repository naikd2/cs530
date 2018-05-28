package com.dhruvitnaik.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

@Region(value = "Report")
public class Report implements Serializable {

	@Id
	private String name;

	private String description;

	private Long map;

	public Report() {
		// pdx
	}

	public Report(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMap() {
		return map;
	}

	public void setMap(Long map) {
		this.map = map;
	}
}

//	@Id
//	private Long id;
//	private static AtomicLong COUNTER = new AtomicLong(0L);
//
//	private String firstName;
//	private String lastName;
//
//	@PersistenceConstructor
//	public Person() {
//		this.id = COUNTER.incrementAndGet();
//	}
