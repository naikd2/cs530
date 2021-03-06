package com.dhruvitnaik.dao;


import com.dhruvitnaik.model.Report;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReportDao extends CrudRepository<Report, String> {

	Report findByName(String name);

	@Query("SELECT * FROM /Report")
	List<Report> getAll();

}
