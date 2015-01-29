package com.myproperty.metadata.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROPERTY")
public class Property implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany
	@JoinTable(name = "PROPERTY_PROPERTY_FIELD", joinColumns = @JoinColumn(name = "PROPERTY_ID"), inverseJoinColumns = @JoinColumn(name = "FIELD_ID"))
	private Collection<PropertyField> propertyFieldSet = new HashSet();

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

	public Collection<PropertyField> getPropertyFieldSet() {
		return propertyFieldSet;
	}

	public void setPropertyFieldSet(Collection<PropertyField> propertyFieldSet) {
		this.propertyFieldSet = propertyFieldSet;
	}

}
