package com.dhruvitnaik.controller;

import com.dhruvitnaik.dao.MapDao;
import com.dhruvitnaik.dao.PinDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Map;
import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.Report;
import com.dhruvitnaik.model.view.Point;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {

	@Autowired
	ReportDao dao;

	@Autowired
	PinDao pinDao;

	@Autowired
	MapDao mapDao;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("index");
		mvc.addObject("reports", dao.getAll());
		return mvc;
	}

	@RequestMapping("{name}/show")
	public ModelAndView show(@PathVariable("name") String name) {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("greeting");
		Report report = dao.findByName(name);
		Map map = mapDao.findById(report.getMap()).get();

		List<Pin> pins = new ArrayList<>();
		pinDao.findAllById(map.getPins()).forEach(pins::add);

		mvc.addObject("pin", new Pin());
		mvc.addObject("point", new Point());
		mvc.addObject("report", report);
		mvc.addObject("map", map);
		mvc.addObject("pins", pins);
		return mvc;
	}

	@RequestMapping(value = "{name}/drop", method = RequestMethod.POST)
	public ModelAndView drop(@PathVariable("name") String name, @ModelAttribute Pin pin, BindingResult bindingResult) {
		for (ObjectError objectError : bindingResult.getAllErrors()) {
			System.out.println(objectError);
		}

		pin.setId(IdGenerator.INSTANCE.getNextPinId());
		pinDao.save(pin);
		return show(name);
	}

	@RequestMapping(value = "{name}/center", method = RequestMethod.POST)
	public ModelAndView center(@ModelAttribute Point point, @PathVariable("name") String name) {
		return show(name);
	}

}
