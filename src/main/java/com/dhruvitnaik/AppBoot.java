package com.dhruvitnaik;

import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppBoot implements ApplicationRunner {

	@Autowired
	private ReportDao reportDao;

	@Autowired
	private MapDao mapDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Report ebola = new Report("ebola");
		ebola.setDescription("ebola is bad");
		Map ebolaMap = new Map();

		ebolaMap.setId(IdGenerator.INSTANCE.getNextMapId());
		ebolaMap.setLatitude(51.505);
		ebolaMap.setLongitude(-0.09);

		ebola.setMap(ebolaMap.getId());

		reportDao.save(ebola);
		mapDao.save(ebolaMap);
	}

}

