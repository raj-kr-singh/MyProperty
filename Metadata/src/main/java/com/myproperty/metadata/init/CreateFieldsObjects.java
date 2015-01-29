package com.myproperty.metadata.init;


import com.myproperty.metadata.converter.ObjectConverter;
import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.PropertyField;
import com.myproperty.metadata.type.PropertyFieldDto;
import com.myproperty.metadata.type.PropertyObjectField;
import com.myproperty.metadata.xml.PropertyObjectFieldConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Component("createFieldsObjects")
public class CreateFieldsObjects {

	@Autowired
	private PropertyDao propertyDao;

	private static final Logger logger = LoggerFactory.getLogger(CreateFieldsObjects.class);
	private Map<Long, Set<PropertyField>> propertyObjectFieldMap = new HashMap();

	@PostConstruct
	private void insertPropertyFieldObject() {
		try {
			processPropertyFieldFile();
		} catch (JAXBException exception) {
			logger.debug("Exception in inserting to database ", exception);
		}

	}

	private void processPropertyFieldFile() throws JAXBException {

		final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = null;

		try {
			resources = resolver.getResources("classpath:com/myproperty/metadata/property/columns/version/1_0/*.xml");
		} catch (IOException exception) {
			logger.error("Error getting Resources", exception);
		}

		for (final Resource resource : resources) {
			PropertyObjectFieldConverter propertyObjectFieldConverter = null;
			Set<PropertyField> tempPropertyField = new HashSet();
			try {
				propertyObjectFieldConverter = new PropertyObjectFieldConverter(resource.getFile());
			} catch (IOException exception) {
				logger.error("Exception in getting file from resource", exception);
			}

			PropertyObjectField propertyObjectField = propertyObjectFieldConverter.getPropertyObjectType();
			List<PropertyFieldDto> propertyFieldDtoList = propertyObjectField.getPropertyFieldList();

			for (PropertyFieldDto propertyFieldDto : propertyFieldDtoList) {

				PropertyField propertyField = new PropertyField();
				propertyField = (PropertyField) ObjectConverter.getCopiedTarget(propertyFieldDto, propertyField);
				tempPropertyField.add(propertyField);
			}

			propertyObjectFieldMap.put(propertyObjectField.getPropertyId(), tempPropertyField);

		}
	}

	public Map<Long, Set<PropertyField>> getPropertyObjectFieldMap() {
		return propertyObjectFieldMap;
	}

	public void setPropertyObjectFieldMap(Map<Long, Set<PropertyField>> propertyObjectFieldMap) {
		this.propertyObjectFieldMap = propertyObjectFieldMap;
	}

}
