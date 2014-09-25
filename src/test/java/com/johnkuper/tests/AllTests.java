package com.johnkuper.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OwnMapperTest.class, ValidatorOfClassesTest.class,
		MapperProviderTest.class, ObjectForMappingFactoryTest.class })
public class AllTests {

}
