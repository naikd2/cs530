package com.dhruvitnaik;

import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.service.ReportService;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AppBoot implements ApplicationRunner {

//	@Autowired
//	private ReportDao reportDao;
//	@Autowired
//	private MapDao mapDao;
//	@Autowired
//	private ReportService service;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Test scenarios can be placed here
		System.err.println("Starting App");
	}

}

