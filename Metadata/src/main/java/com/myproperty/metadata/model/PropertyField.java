package com.myproperty.metadata.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROPERTY_FIELD", uniqueConstraints = {@UniqueConstraint(columnNames = {"FIELD_ID", "COLUMN_NAME", "PROPERTY_ID"})})
public class PropertyField implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FIELD_ID", unique = true)
	private long id;

	@ManyToOne
	@JoinColumn(name = "PROPERTY_ID")
	private Property property;

	@Column(name = "MANDATORY", nullable = false)
	private boolean mandatory;

	@Column(name = "COLUMN_NAME", nullable = false)
	private String columnName;

	@Column(name = "COLUMN_TYPE", nullable = false)
	private String columnType;

	@Column(name = "UNIQUE_INCLUSION", nullable = false)
	private boolean uniqueInclusion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public boolean isUniqueInclusion() {
		return uniqueInclusion;
	}

	public void setUniqueInclusion(boolean uniqueInclusion) {
		this.uniqueInclusion = uniqueInclusion;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
