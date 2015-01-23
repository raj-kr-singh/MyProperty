package com.myproperty.metadata.init;


import com.myproperty.metadata.converter.ObjectConverter;
import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import com.myproperty.metadata.type.PropertyObjectDto;
import com.myproperty.metadata.xml.PropertyObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository("createPropertyObjects")
public class CreatePropertyObjects {

	@Autowired
	PropertyDao propertyDao;
	private static final Logger logger = LoggerFactory.getLogger(CreatePropertyObjects.class);


	@PostConstruct
	public void insertObject() {
		try {
			processPropertyFile();
		} catch (JAXBException exception) {
			logger.debug("Exception in inserting to database ", exception);
		}

	}


	private Map<Long, PropertyObjectDto> processPropertyFile() throws JAXBException {

		final Map<Long, PropertyObjectDto> propertyObjectMap = new HashMap();
		final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = null;

		try {
			resources= resolver.getResources("classpath:com/myproperty/metadata/property/type/*.xml");
		} catch (IOException exception) {
			logger.error("Error getting Resources", exception);
		}


		for (final Resource resource : resources) {
			PropertyObjectConverter propertyObjectConverter = null;
			try {
				propertyObjectConverter = new PropertyObjectConverter(resource.getFile());
			} catch (IOException exception) {
				logger.error("Exception while reading property type file ", exception);
			}

			PropertyObjectDto propertyObjectDto = propertyObjectConverter.getPropertyObjectType();
			propertyObjectMap.put(propertyObjectDto.getId(), propertyObjectDto);

			Property property = new Property();
			property = (Property) ObjectConverter.getCopiedTarget(propertyObjectDto, property);

			propertyDao.saveProperty(property);

		}

		return propertyObjectMap;

	}

}
