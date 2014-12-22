package com.myproperty.metadata.xml;

import com.myproperty.metadata.type.PropertyObjectField;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PropertyObjectFieldConverter {
	private File propertyColumnFile;

	public PropertyObjectFieldConverter(File propertyColumnFile) {
		this.propertyColumnFile = propertyColumnFile;
	}

	public PropertyObjectField getPropertyObjectType() throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(PropertyObjectField.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (PropertyObjectField) jaxbUnmarshaller.unmarshal(propertyColumnFile);

	}
}
