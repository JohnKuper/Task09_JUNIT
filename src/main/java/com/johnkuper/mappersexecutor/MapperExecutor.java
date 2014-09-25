package com.johnkuper.mappersexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.factory.ObjectForMappingFactory;
import com.johnkuper.mapper.MapperProvider;
import com.johnkuper.mapper.OwnMapper;
import com.johnkuper.mapper.ValidatorOfClasses;
import com.johnkuper.tester.MapperTester;

public class MapperExecutor {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	static ObjectForMappingFactory factory = new ObjectForMappingFactory();
	static MapperTester tester = new MapperTester();

	public static void main(String[] args) {

		CreditCard srcObject = factory.getCreditCard();
		BillingDetails destiObject = factory.getBillingDetails();
		ValidatorOfClasses validator = new ValidatorOfClasses();
		MapperProvider provider = new MapperProvider(srcObject.getClass(),
				destiObject.getClass(), validator);
		OwnMapper ownMapper = new OwnMapper(srcObject, destiObject, provider);

		try {
			logger.debug("=================");
			logger.debug("Own mapper starts");
			ownMapper.map();
			tester.outputSourceData(srcObject);
			tester.outputDestiData(destiObject);
		} catch (OwnMapperException e) {
			logger.error("Program will be terminated, because: ", e);
		} finally {
			logger.debug("Own mapper complete");

		}

	}
}
