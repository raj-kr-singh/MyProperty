package com.myproperty.metadata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopLevelProperty {

	@RequestMapping(value = "/home")
	@ResponseBody
	public List<String> getTopLevelPropetyNames() {
		List<String> topLevelPropetyNames = new ArrayList();

		return topLevelPropetyNames;
	}


}
