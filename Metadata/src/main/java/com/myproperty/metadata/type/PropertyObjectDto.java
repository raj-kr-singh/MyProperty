package com.myproperty.metadata.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "propertyObject")
public class PropertyObjectDto {

	private long id;
	private String name;
	private boolean topLevel;
	private long parentId;
	private boolean isProcessed;

	public long getId() {
		return id;
	}

	@XmlElement
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public boolean isTopLevel() {
		return topLevel;
	}

	@XmlElement
	public void setTopLevel(boolean topLevel) {
		this.topLevel = topLevel;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean processed) {
		isProcessed = processed;
	}

	public long getParentId() {
		return parentId;
	}

	@XmlElement
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
