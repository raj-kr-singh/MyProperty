package com.myproperty.metadata.controller;

import com.myproperty.metadata.dao.PropertyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TopLevelProperty {

	@Autowired
	PropertyDao propertyDao;

	@RequestMapping(value = "/home")
	@ResponseBody
	public List<String> getTopLevelPropertyNames() {

		return propertyDao.getTopLevelProperty();
	}


}
