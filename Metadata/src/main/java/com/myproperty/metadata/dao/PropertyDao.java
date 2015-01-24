package com.myproperty.metadata.dao;

import com.myproperty.metadata.model.Property;

import java.util.List;

public interface PropertyDao {

	void saveProperty(Property property);

	List<Property> getTopLevelProperties();

	List<Property> getAllProperties();

	Property getProperty(long propertyId);
}