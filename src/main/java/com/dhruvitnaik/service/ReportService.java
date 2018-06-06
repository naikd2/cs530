package com.dhruvitnaik.service;

import com.dhruvitnaik.dao.FilterDao;
import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.PinDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Filter;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.model.view.Point;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportService {

	@Autowired
	private ReportDao reportDao;

	@Autowired
	private FilterDao filterDao;

	@Autowired
	private MapDao mapDao;

	@Autowired
	private PinDao pinDao;

	@Autowired
	private CriteriaFilterService filterService;

	private IdGenerator generator = IdGenerator.INSTANCE;

	public void saveReport(Report report) {
		if (report.getMap() == null) {
			Map map = saveMap(new Map());
			report.setMap(map.getId());
		}
		reportDao.save(report);
	}

	public void saveFilter(Filter filter, Report report) {
		if (filter.getId() == null) {
			filter.setId(generator.getNextFilterId());
		}
		filter = filterService.buildExpression(filter);
		filterDao.save(filter);
		report.getCriteria().add(filter.getId());
		reportDao.save(report);
	}

	public void deleteFilter(Long filterId, Report report) {
		filterDao.findById(filterId).ifPresent(f-> {
			filterDao.delete(f);
			report.getCriteria().remove(filterId);
			reportDao.save(report);
		});
	}


	public void savePin(Pin pin, Report report) {
		mapDao.findById(report.getMap()).ifPresent(m -> {
			if (pin.getId() == null) {
				pin.setId(generator.getNextPinId());
			}
			pinDao.save(pin);
			m.getPins().add(pin.getId());
			mapDao.save(m);
		});
	}

	public void deletePin(Long pinId, Report report) {
		mapDao.findById(report.getMap()).ifPresent(m ->
			pinDao.findById(pinId).ifPresent(p -> {
				pinDao.delete(p);
				m.getPins().remove(pinId);
				mapDao.save(m);
			})
		);
	}

	public Map saveMap(Map map) {
		if (map.getId() == null) {
			map.setId(generator.getNextMapId());
			// Center Map to London
			map.setLatitude(51.505);
			map.setLongitude(-0.09);
		}
		mapDao.save(map);
		return map;
	}

	public List<Filter> getCriteria(Report report) {
		List<Filter> criteria = new ArrayList<>();
		for (Long key : report.getCriteria()) {
			filterDao.findById(key).ifPresent(c-> criteria.add(c));
		}
		return criteria;
	}

	public List<Pin> filterReport(Report report, Map map) {
		List<Filter> criteria = getCriteria(report);
		List<Pin> pins = new ArrayList<>();
		for (Long key : map.getPins()) {
			pinDao.findById(key).ifPresent( p -> {
				boolean valid = true;
				for (Filter f : criteria) {
					if (!filterService.evaluate(p, f)) {
						valid = false;
						break;
					}
				}
				if (valid) {
					pins.add(p);
				}

			});
		}
		return pins;
	}

	public void updateCenter(Report report, Point center) {
		Map map = mapDao.findById(report.getMap()).orElse(null);
		if (map != null) {
			map.setLongitude(center.getLongitude());
			map.setLatitude(center.getLatitude());
			mapDao.save(map);
		}
	}
}
