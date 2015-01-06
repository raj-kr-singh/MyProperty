package com.myproperty.metadata.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "propertyObjectField")
public class PropertyObjectField {

	private long propertyId;
	private List<PropertyFieldDto> propertyFieldList = null;

	public List<PropertyFieldDto> getPropertyFieldList() {
		return propertyFieldList;
	}

	@XmlElement(name = "propertyField")
	public void setPropertyFieldList(List<PropertyFieldDto> propertyFieldList) {
		this.propertyFieldList = propertyFieldList;
	}

	public long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
