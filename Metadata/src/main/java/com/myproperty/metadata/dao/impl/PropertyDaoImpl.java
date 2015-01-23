package com.myproperty.metadata.dao.impl;

import com.myproperty.metadata.dao.PropertyDao;
import com.myproperty.metadata.model.Property;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
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
	public List<String> getTopLevelProperty() {

		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Property.class);
		criteria.setProjection(Projections.property("name"));
		criteria.add(Restrictions.eq("parentId", 0));
		criteria.setResultTransformer(Transformers.aliasToBean(String.class));
		return criteria.list();

	}

}
