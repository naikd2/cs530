package com.dhruvitnaik.dao;


import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.Report;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PinDao extends CrudRepository<Pin, Long> {

	@Query("SELECT * FROM /Pin")
	Collection<Pin> getAll();

}
