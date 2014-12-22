package com.myproperty.metadata.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ObjectConverter {

	private static final Logger logger = LoggerFactory.getLogger(ObjectConverter.class);

	public static Object getCopiedTarget(Object fromObject, Object toObject) {
		logger.info("converting object ...");
		BeanUtils.copyProperties(fromObject, toObject);
		return toObject;

	}

}
