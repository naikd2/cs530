package com.dhruvitnaik.dao;

import com.dhruvitnaik.model.Filter;
import com.dhruvitnaik.model.Map;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FilterDao extends CrudRepository<Filter, Long> {

	@Query("SELECT * FROM /Filter")
	Collection<Filter> getAll();

}
