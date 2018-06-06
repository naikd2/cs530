package com.dhruvitnaik.controller;

import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.PinDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Filter;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.model.view.Point;
import com.dhruvitnaik.service.CriteriaFilterService;
import com.dhruvitnaik.service.ReportService;
import com.dhruvitnaik.util.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

	@Autowired
	ReportDao dao;

	@Autowired
	PinDao pinDao;

	@Autowired
	MapDao mapDao;

	@Autowired
	private ReportService service;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("index");
		mvc.addObject("newReport", new Report());
		mvc.addObject("reports", dao.getAll());
		return mvc;
	}

	@RequestMapping("{name}/show")
	public ModelAndView show(@PathVariable("name") String name) {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("greeting");

		Report report = dao.findByName(name);
		List<Report> reports = dao.getAll();
		Map map = mapDao.findById(report.getMap()).orElse(null);

		// Filter Pins Based on Criteria Filter
		List<Pin> pins = service.filterReport(report, map);
		String json = createJson(pins);


		mvc.addObject("pin", new Pin());
		mvc.addObject("point", new Point());
		mvc.addObject("newReport", new Report());
		mvc.addObject("report", report);
		mvc.addObject("reports", reports);
		mvc.addObject("map", map);
		mvc.addObject("pinJson", json);
		mvc.addObject("pins", pins);

		return mvc;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView createReport(@ModelAttribute Report report, BindingResult bindingResult) {
		service.saveReport(report);
		return show(report.getName());
	}

	private String createJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return "";
	}

	@RequestMapping(value = "{name}/drop", method = RequestMethod.POST)
	public ModelAndView drop(@PathVariable("name") String name, @ModelAttribute Pin pin, BindingResult bindingResult) {
		for (ObjectError objectError : bindingResult.getAllErrors()) {
			System.out.println(objectError);
		}
		service.savePin(pin, dao.findByName(name));
		return show(name);
	}

	@RequestMapping(value = "{name}/pin/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deletePin(@PathVariable("name") String name, @PathVariable("id") Long id) {
		service.deletePin(id, dao.findByName(name));
		return show(name);
	}

	@RequestMapping(value = "{name}/center", method = RequestMethod.POST)
	public ModelAndView center(@ModelAttribute Point point, @PathVariable("name") String name) {
		Report report = dao.findByName(name);
		service.updateCenter(report, point);
		return show(name);
	}

	@RequestMapping(value = "{name}/criteria", method = RequestMethod.GET)
	public ModelAndView criteria(@PathVariable("name") String name) {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("criteria");

		Report report = dao.findByName(name);
		List<Filter> criteria = service.getCriteria(report);

		mvc.addObject("filter", new Filter());
		mvc.addObject("report", report);
		mvc.addObject("criteria", criteria);

		return mvc;
	}

	@RequestMapping(value = "{name}/criteria", method = RequestMethod.POST)
	public ModelAndView createCriteria(@PathVariable("name") String name, @ModelAttribute Filter filter, BindingResult bindingResult) {
		service.saveFilter(filter,dao.findByName(name));
		return criteria(name);
	}

	@RequestMapping(value = "{name}/criteria/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCriteria(@PathVariable("name") String name, @PathVariable("id") Long id) {
		service.deleteFilter(id, dao.findByName(name));
		return criteria(name);
	}

}
