package com.myproperty.metadata.dao.impl;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List<Property> getTopLevelProperties() {

		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Property.class);
		criteria.add(Restrictions.eq("parentId", 0L));
		try {
			return criteria.list();
		} catch (Exception exception) {
			logger.error("Exception while getting Top Level Properties", exception);
		}

		return null;
	}

	@Override
	public List<Property> getAllProperties() {

		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Property.class);
		try {
			return criteria.list();
		} catch (Exception exception) {
			logger.error("Exception while getting All Properties", exception);
		}

		return null;
	}

}
