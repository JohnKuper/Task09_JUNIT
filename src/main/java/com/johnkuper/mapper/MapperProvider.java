package com.johnkuper.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.annotations.FieldAnnotation;
import com.johnkuper.exceptions.OwnMapperException;

public class MapperProvider {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");
	private Class<?> srcClass;
	private Class<?> destiClass;
	private Map<String, String> fieldsNamesMap = new TreeMap<>();
	private ValidatorOfClasses validator;

	public MapperProvider(Class<?> srcClass, Class<?> destiClass, ValidatorOfClasses validator) {
		this.srcClass = srcClass;
		this.destiClass = destiClass;
		this.validator = validator;
		this.fieldsNamesMap = getMapOfFieldsNames();

	}

	public boolean verifyMappingModel() throws OwnMapperException {

		if (validator.isAnnotationNameEqualWithOutputClassName(srcClass,
				destiClass)) {
			logger.debug("Output class name verification is successfully");
			return true;
		}
		return false;
	}

	public Field[] getFieldsFromSourceClass() {
		logger.debug("Getting fields from source class");
		Field[] srcFields = srcClass.getDeclaredFields();
		return srcFields;

	}

	public Map<String, String> getMapOfFieldsNames() {

		try {
			if (verifyMappingModel()) {
				logger.debug("Start searching field assignment for "
						+ srcClass.getSimpleName());
				Field[] srcfields = getFieldsFromSourceClass();
				for (Field srcField : srcfields) {
					String sourceFieldName = srcField.getName();
					String targetFieldName = null;

					if (srcField.isAnnotationPresent(FieldAnnotation.class)) {
						Annotation fieldAnnotation = srcField
								.getAnnotation(FieldAnnotation.class);
						targetFieldName = ((FieldAnnotation) fieldAnnotation)
								.name();
						logger.debug("Field '{}' map to '{}': ",
								sourceFieldName, targetFieldName);
						fieldsNamesMap.put(sourceFieldName, targetFieldName);
					}
				}
			}
		} catch (OwnMapperException e) {
			String msg = e.toString();
			logger.error(msg);
		}
		logger.debug("Searching finished");
		return fieldsNamesMap;
	}

	public List<FieldsContainer> getFieldsForMapping()
			throws OwnMapperException {

		List<FieldsContainer> listOfContainers = new ArrayList<>();
		Set<String> keys = fieldsNamesMap.keySet();
		String srcFieldName = null;
		String targetFieldName = null;
		Field srcField = null;
		Field targetField = null;
		logger.debug("Collecting fields for mapping: "
				+ srcClass.getSimpleName() + " to "
				+ destiClass.getSimpleName());
		try {
			for (String value : keys) {
				srcFieldName = value;
				targetFieldName = fieldsNamesMap.get(srcFieldName);

				srcField = srcClass.getDeclaredField(srcFieldName);
				targetField = destiClass.getDeclaredField(targetFieldName);

				if (srcField.getType().equals(targetField.getType())) {
					FieldsContainer container = new FieldsContainer(srcField,
							targetField);
					listOfContainers.add(container);
				}
			}

		} catch (NoSuchFieldException ex) {
			logger.error("Field '{}' not found at '{}' class", targetFieldName,
					destiClass.getSimpleName());
			throw new OwnMapperException(ex);
		}

		return listOfContainers;
	}

}
