package com.dhruvitnaik;

import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Arrays;

@SpringBootApplication
@ClientCacheApplication(name = "GemFireCache", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Report.class,
		clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class Cs530Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Cs530Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringApplication.class);
	}

}
