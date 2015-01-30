package com.myproperty.metadata.dao;

import com.myproperty.metadata.model.PropertyField;

import java.util.Collection;
import java.util.List;

public interface PropertyFieldDao {

	void savePropertyField(PropertyField propertyField);

	List<PropertyField> getPropertyField(String propertyId);

}
