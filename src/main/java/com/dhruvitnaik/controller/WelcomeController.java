package com.dhruvitnaik.controller;

import com.dhruvitnaik.dao.PinDao;
import com.dhruvitnaik.dao.ReportDao;
import com.dhruvitnaik.model.Pin;
import com.dhruvitnaik.model.view.Point;
import com.dhruvitnaik.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@Autowired
	ReportDao dao;

	@Autowired
	PinDao pinDao;

	@RequestMapping("/greeting")
	public ModelAndView welcome() {
		ModelAndView mvc = new ModelAndView();
		mvc.setViewName("greeting");
		mvc.addObject("pin", new Pin());
		mvc.addObject("point", new Point());
		mvc.addObject("message", "Hello Dhruvit");
		return mvc;
	}

	@RequestMapping(value = "/dropPin", method = RequestMethod.POST)
	public ModelAndView dropPin(@ModelAttribute Pin pin) {
		pin.setId(IdGenerator.INSTANCE.getNextPinId());
		pinDao.save(pin);

		return welcome();
	}

	@RequestMapping(value = "/center", method = RequestMethod.POST)
	public ModelAndView center(@ModelAttribute Point point) {
		return welcome();
	}

}
