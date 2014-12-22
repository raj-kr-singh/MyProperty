package com.myproperty.metadata.xml;

import com.myproperty.metadata.type.PropertyObjectDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PropertyObjectConverter {

	private File propertyFile;

	public PropertyObjectConverter(File propertyFile) {
		this.propertyFile = propertyFile;
	}

	public PropertyObjectDto getPropertyObjectType() throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(PropertyObjectDto.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (PropertyObjectDto) jaxbUnmarshaller.unmarshal(propertyFile);

	}

}
