package com.myproperty.metadata.dao.impl;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("propertyDao")
@Transactional
public class PropertyDaoImpl extends HibernateDaoSupport implements PropertyDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveProperty(Property property) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().save(property);
	}

	@Override
	public Property getProperty(long propertyId) {
		return getHibernateTemplate().load(Property.class, propertyId);
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
