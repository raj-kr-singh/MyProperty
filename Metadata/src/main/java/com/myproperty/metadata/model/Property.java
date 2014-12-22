package com.myproperty.metadata.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROPERTY",uniqueConstraints = @UniqueConstraint(columnNames = "PROPERTY_ID"))
public class Property implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
	@JoinColumn(name = "PROPERTY_ID")
	private Set<PropertyField> propertyFieldSet = new HashSet<PropertyField>();

	@Id
	@Column(name = "PROPERTY_ID", unique = true)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TOPLEVEL")
	private boolean topLevel;

	@Column(name = "PARENTID")
	private long parentId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTopLevel() {
		return topLevel;
	}

	public void setTopLevel(boolean topLevel) {
		this.topLevel = topLevel;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public Set<PropertyField> getPropertyFieldSet() {
		return propertyFieldSet;
	}

	public void setPropertyFieldSet(Set<PropertyField> propertyFieldSet) {
		this.propertyFieldSet = propertyFieldSet;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
