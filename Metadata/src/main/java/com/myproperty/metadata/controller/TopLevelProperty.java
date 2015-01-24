package com.myproperty.metadata.controller;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TopLevelProperty {

	@Autowired
	PropertyDao propertyDao;

	@RequestMapping("/topLevelProperty")
	@ResponseBody
	public ModelAndView getTopLevelPropertyNames() {

		ModelAndView modelAndView = new ModelAndView("topProperty");
		final List<Property> topLevelProperty = propertyDao.getTopLevelProperty();
		modelAndView.addObject("properties", topLevelProperty);
		return modelAndView;
	}


}
