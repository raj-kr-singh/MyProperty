package com.myproperty.metadata.dao.impl;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import com.myproperty.metadata.type.PropertyFieldDto;
import com.myproperty.metadata.type.PropertyObjectField;
import com.myproperty.metadata.xml.PropertyObjectFieldConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/myproperty/metadata/config/metadata-spring.xml")
public class PropertyDaoImplTest {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected ApplicationContext applicationContext;

	@Test
	public void insert_retrieve_property() {
		Property property = new Property();

		property.setName("Building");
		PropertyDao propertyDao = applicationContext.getBean("propertyDao", PropertyDao.class);
		propertyDao.saveProperty(property);

	}

	@Test
	public void insert_property_fields() throws JAXBException {
		final File propertyFolder = new File(ClassLoader.getSystemResource("com/myproperty/metadata/property/columns/version/1_0").getFile());
		final Map<Long, PropertyObjectField> propertyObjectFieldMap = new HashMap();

		for (final File fileEntry : propertyFolder.listFiles()) {
			PropertyObjectFieldConverter propertyObjectFieldConverter = new PropertyObjectFieldConverter(fileEntry);
			PropertyObjectField propertyObjectField = propertyObjectFieldConverter.getPropertyObjectType();
			propertyObjectFieldMap.put(propertyObjectField.getPropertyId(), propertyObjectField);
		}

		Set<Long> propertyIdSet = propertyObjectFieldMap.keySet();
		for (Long propertyId : propertyIdSet) {
			List<PropertyFieldDto> propertyObjectFieldList = propertyObjectFieldMap.get(propertyId).getPropertyFieldList();
			for (PropertyFieldDto propertyField : propertyObjectFieldList) {

			}


		}


	}

}
