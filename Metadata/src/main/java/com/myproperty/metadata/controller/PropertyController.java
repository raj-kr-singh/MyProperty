package com.myproperty.metadata.controller;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PropertyController {

	@Autowired
	PropertyDao propertyDao;

	@RequestMapping(value = "/topProperties")
	@ResponseBody
	public ModelAndView getTopLevelProperties() {

		ModelAndView modelAndView = new ModelAndView("topProperties");
		final List<Property> topProperties = propertyDao.getTopLevelProperties();
		modelAndView.addObject("properties", topProperties);
		return modelAndView;
	}

	@RequestMapping("/properties")
	@ResponseBody
	public ModelAndView getAllProperties() {

		ModelAndView modelAndView = new ModelAndView("properties");
		final List<Property> properties = propertyDao.getAllProperties();
		modelAndView.addObject("properties", properties);
		return modelAndView;
	}

}
