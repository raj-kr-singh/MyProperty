package com.myproperty.metadata.dao.impl;

import com.myproperty.metadata.dao.PropertyFieldDao;
import com.myproperty.metadata.model.PropertyField;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("propertyFieldDao")
@Transactional
public class PropertyFieldDaoImpl extends HibernateDaoSupport implements PropertyFieldDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void savePropertyField(PropertyField property) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().save(property);
	}

	@Override
	public List<PropertyField> getPropertyField(String propertyId) {
		return getHibernateTemplate().find("from Property where propertyId = " + propertyId);
	}
}
