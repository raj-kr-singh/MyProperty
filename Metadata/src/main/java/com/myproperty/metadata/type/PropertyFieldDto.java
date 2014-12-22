package com.myproperty.metadata.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "propertyField")
public class PropertyFieldDto {

	private long id;
	private boolean mandatory;
	private String columnName;
	private String columnType;
	private boolean uniqueInclusion;

	public long getId() {
		return id;
	}

	@XmlElement
	public void setId(long id) {
		this.id = id;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	@XmlElement
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getColumnName() {
		return columnName;
	}

	@XmlElement
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	@XmlElement
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public boolean isUniqueInclusion() {
		return uniqueInclusion;
	}

	@XmlElement
	public void setUniqueInclusion(boolean uniqueInclusion) {
		this.uniqueInclusion = uniqueInclusion;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
