package com.dhruvitnaik.dao;


import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Report;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MapDao extends CrudRepository<Map, Long> {

	@Query("SELECT * FROM /Map")
	Collection<Map> getAll();

}
