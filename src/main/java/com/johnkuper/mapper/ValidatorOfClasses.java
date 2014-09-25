package com.johnkuper.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.annotations.ClassAnnotation;
import com.johnkuper.exceptions.OwnMapperException;

public class ValidatorOfClasses {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	public boolean isAnnotationNameEqualWithOutputClassName(Class<?> input,
			Class<?> output) throws OwnMapperException {
		logger.debug("Trying to compare annotation name and output class name");
		if (!input.isAnnotationPresent(ClassAnnotation.class)) {
			String msg = String
					.format("Source class -%s- not contain required annotation @ClassAnnotation",
							input.getSimpleName());
			throw new OwnMapperException(msg);
		}
		Annotation annotation = input.getAnnotation(ClassAnnotation.class);
		ClassAnnotation classAnotation = (ClassAnnotation) annotation;
		String annName = classAnotation.name();
		String toClassName = output.getSimpleName();
		if (toClassName.equals(annName)) {
			logger.debug(
					"Annotation name = {}, output class name = {} and they are equals",
					annName, toClassName);
			return true;
		} else {
			logger.debug(
					"Annotation name = {}, output class name = {} and they are not equals",
					annName, toClassName);
			String msg = "@ClassAnnotation name and output class name not equal.";
			throw new OwnMapperException(msg);
		}

	}

	public boolean isBothClassesContainFields(Class<?> input, Class<?> output) {

		Field[] srcFields = input.getDeclaredFields();
		Field[] destiFields = output.getDeclaredFields();

		if (srcFields.length > 0 && destiFields.length > 0) {
			return true;
		}
		return false;
	}
}
