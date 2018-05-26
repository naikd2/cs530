package com.dhruvitnaik;

import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Report;
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
@ClientCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "error")
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

	@Bean
	ApplicationRunner run(ReportDao dao) {

		return args -> {

			Report ebola = new Report("ebola");
			Report influenza = new Report("influenza");
			Report aids = new Report("aids");


			System.out.println("Before accessing data in Pivotal GemFire...");

			Arrays.asList(ebola, influenza, aids).forEach(person -> System.out.println("\t" + person));

			System.out.println("Saving Alice, Bob and Carol to Pivotal GemFire...");

			dao.save(ebola);
			dao.save(influenza);
			dao.save(aids);

			System.out.println("Lookup each person by name...");

			Arrays.asList(ebola.getName(), influenza.getName(), aids.getName())
					.forEach(name -> System.out.println("\t" + dao.findByName(name)));

			ebola = dao.findByName("ebola");
			ebola.setDescription("this is a description");

			dao.save(ebola);

			System.out.println("Pivotal GemFire..." + dao.getAll().size());

			dao.getAll().forEach(r -> {
					System.out.println(r.getName() + " : " + r.getDescription());
			});
		};
	}
}
