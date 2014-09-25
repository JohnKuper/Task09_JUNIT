package com.johnkuper.mapper;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.exceptions.OwnMapperException;

public class OwnMapper {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");
	private Object srcObject;
	private Object destiObject;
	private Class<?> srcClass;
	private Class<?> destiClass;
	private MapperProvider provider;
	

	public OwnMapper(Object srcObject, Object destiObject,
			MapperProvider provider) {
		this.srcObject = srcObject;
		this.destiObject = destiObject;
		this.srcClass = srcObject.getClass();
		this.destiClass = destiObject.getClass();
		this.provider = provider;
		
	}

	public Object map() throws OwnMapperException {

		List<FieldsContainer> listOfContainers = provider.getFieldsForMapping();
		Field srcField = null;
		Field targetField = null;
		logger.debug("Start mapping process: " + srcClass.getSimpleName()
				+ " to " + destiClass.getSimpleName());
		if (listOfContainers != null) {
			try {
				for (FieldsContainer container : listOfContainers) {

					srcField = container.getSrcField();
					targetField = container.getDestiField();
					srcField.setAccessible(true);
					targetField.setAccessible(true);

					logger.debug(
							"Get value from source field '{}' and set value to output field '{}'",
							srcField.getName(), targetField.getName());
					targetField.set(destiObject, srcField.get(srcObject));

				}

			} catch (IllegalAccessException ex) {
				String msg = ex.toString();
				logger.error(msg);
				throw new OwnMapperException(msg);
			}
			return destiObject;
		} else {
			return null;
		}

	}
}
