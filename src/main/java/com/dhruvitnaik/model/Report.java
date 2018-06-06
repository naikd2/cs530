package com.dhruvitnaik.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Region(value = "Report")
public class Report implements Serializable {

	@Id
	private String name;

	private String description;

	private Long map;

	private List<Long> criteria = new ArrayList<>(5);

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

	public List<Long> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Long> criteria) {
		this.criteria = criteria;
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
