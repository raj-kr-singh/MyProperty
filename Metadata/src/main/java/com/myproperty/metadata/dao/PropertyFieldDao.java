package com.myproperty.metadata.dao;

import com.myproperty.metadata.model.PropertyField;

import java.util.List;

public interface PropertyFieldDao {

	public void savePropertyField(PropertyField property);

	public List<PropertyField> getPropertyField(String propertyId);

}
