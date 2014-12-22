package com.myproperty.metadata.dao;

import com.myproperty.metadata.model.Property;

import java.util.List;

public interface PropertyDao {

	public void saveProperty(Property property);

	public Property getProperty(long propertyId);
}