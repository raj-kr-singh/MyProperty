package com.myproperty.metadata.util;

import com.myproperty.metadata.converter.ObjectConverter;
import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.dao.PropertyFieldDao;
import com.myproperty.metadata.hierarchy.TraversalStrategy;
import com.myproperty.metadata.hierarchy.Tree;
import com.myproperty.metadata.model.Property;
import com.myproperty.metadata.model.PropertyField;
import com.myproperty.metadata.type.PropertyFieldDto;
import com.myproperty.metadata.type.PropertyObjectDto;
import com.myproperty.metadata.type.PropertyObjectField;
import com.myproperty.metadata.xml.PropertyObjectConverter;
import com.myproperty.metadata.xml.PropertyObjectFieldConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartMetadata {

	private final static String TOP_NODE_PROPERTY = "My Property";
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		final Map<Long, PropertyObjectDto> propertyObjectMap;
		final Tree propertyTree;

		try {
			applicationContext = new ClassPathXmlApplicationContext("com/myproperty/metadata/config/metadata-spring.xml");

			propertyObjectMap = processPropertyFile();
			propertyTree = new Tree(TraversalStrategy.BREADTH_FIRST);
			propertyTree.addNode(TOP_NODE_PROPERTY);

			for (PropertyObjectDto propertyObject : propertyObjectMap.values()) {
				createPropertyTree(propertyTree, propertyObjectMap, propertyObject);
			}

			propertyTree.display(TOP_NODE_PROPERTY);
			processPropertyFields();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured");
		}
	}

	private static void createPropertyTree(final Tree propertyTree, final Map<Long, PropertyObjectDto> propertyObjectMap, final PropertyObjectDto propertyObject) {

		if (propertyObject.isProcessed()) {
			return;
		}

		if (propertyObject.isTopLevel()) {
			propertyTree.addNode(propertyObject.getName(), TOP_NODE_PROPERTY);
		} else {

			final PropertyObjectDto parentPropertyObject = propertyObjectMap.get(propertyObject.getParentId());

			if (!parentPropertyObject.isProcessed()) {
				createPropertyTree(propertyTree, propertyObjectMap, parentPropertyObject);
			}

			propertyTree.addNode(propertyObject.getName(), propertyObjectMap.get(propertyObject.getParentId()).getName());
		}

		propertyObject.setProcessed(true);

	}

	private static Map<Long, PropertyObjectDto> processPropertyFile() throws JAXBException {

		final File propertyFolder = new File(ClassLoader.getSystemResource("com/myproperty/metadata/property/type").getFile());
		final Map<Long, PropertyObjectDto> propertyObjectMap = new HashMap();
		PropertyDao propertyDao = applicationContext.getBean("propertyDao", PropertyDao.class);

		for (final File fileEntry : propertyFolder.listFiles()) {
			PropertyObjectConverter propertyObjectConverter = new PropertyObjectConverter(fileEntry);
			PropertyObjectDto propertyObjectDto = propertyObjectConverter.getPropertyObjectType();
			propertyObjectMap.put(propertyObjectDto.getId(), propertyObjectDto);

			Property property = new Property();
			property = (Property) ObjectConverter.getCopiedTarget(propertyObjectDto, property);

			propertyDao.saveProperty(property);

		}

		return propertyObjectMap;

	}

	private static Map<Long, PropertyObjectField> processPropertyFields() throws JAXBException {

		final File propertyFolder = new File(ClassLoader.getSystemResource("com/myproperty/metadata/property/columns/version/1_0").getFile());
		final Map<Long, PropertyObjectField> propertyObjectFieldMap = new HashMap();
		PropertyFieldDao propertyFieldDao = applicationContext.getBean("propertyFieldDao", PropertyFieldDao.class);
		PropertyDao propertyDao = applicationContext.getBean("propertyDao", PropertyDao.class);

		for (final File fileEntry : propertyFolder.listFiles()) {
			PropertyObjectFieldConverter propertyObjectFieldConverter = new PropertyObjectFieldConverter(fileEntry);
			PropertyObjectField propertyObjectField = propertyObjectFieldConverter.getPropertyObjectType();
			propertyObjectFieldMap.put(propertyObjectField.getPropertyId(), propertyObjectField);

			List<PropertyFieldDto> propertyFieldDtoList = propertyObjectField.getPropertyFieldList();

			for (PropertyFieldDto propertyFieldDto : propertyFieldDtoList) {

				PropertyField propertyField = new PropertyField();
				propertyField = (PropertyField) ObjectConverter.getCopiedTarget(propertyFieldDto, propertyField);
				propertyField.setProperty(propertyDao.getProperty(propertyObjectField.getPropertyId()));

				propertyFieldDao.savePropertyField(propertyField);

			}


		}

		return propertyObjectFieldMap;

	}

}
