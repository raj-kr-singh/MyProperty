package com.myproperty.metadata.init;


import com.myproperty.metadata.dao.PropertyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository("createPropertyObjects")
public class CreatePropertyObjects {

	@Autowired
	PropertyDao propertyDao;

	@PostConstruct
	public void insertObject() {

	}
}
