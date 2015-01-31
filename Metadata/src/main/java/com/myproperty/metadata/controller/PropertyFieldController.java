package com.myproperty.metadata.controller;

import com.myproperty.metadata.dao.PropertyFieldDao;
import com.myproperty.metadata.model.PropertyField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PropertyFieldController {

	@Autowired
	PropertyFieldDao propertyFieldDao;

	@RequestMapping(value = "/property/{id}/fields")
	@ResponseBody
	public ModelAndView getPropertyFields(@PathVariable Long id) {

		ModelAndView modelAndView = new ModelAndView("fields");
		final List<PropertyField> fields = propertyFieldDao.getPropertyField(id);
		modelAndView.addObject("fields", fields);
		return modelAndView;
	}

}
