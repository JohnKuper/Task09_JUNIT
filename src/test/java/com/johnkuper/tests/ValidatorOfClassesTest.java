package com.johnkuper.tests;

import org.junit.Assert;
import org.junit.Test;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.datafortests.BillingDetailsForTest;
import com.johnkuper.datafortests.CreditCardForTest;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.mapper.FieldsContainer;
import com.johnkuper.mapper.ValidatorOfClasses;

public class ValidatorOfClassesTest {

	ValidatorOfClasses validator = new ValidatorOfClasses();

	@Test(expected = OwnMapperException.class)
	public void source_Class_Without_Required_Annotation_Should_Throw_Exception()
			throws OwnMapperException {

		validator.isAnnotationNameEqualWithOutputClassName(
				FieldsContainer.class, BillingDetails.class);

	}

	@Test(expected = OwnMapperException.class)
	public void annotation_Name_And_Target_Class_Name_Are_Not_Equal_Should_Throw_Exception()
			throws OwnMapperException {

		validator.isAnnotationNameEqualWithOutputClassName(
				CreditCardForTest.class, BillingDetails.class);
	}

	@Test
	public void annotation_Name_Same_With_Target_Class_Name_Should_Return_True()
			throws OwnMapperException {

		boolean check = validator.isAnnotationNameEqualWithOutputClassName(
				CreditCardForTest.class, BillingDetailsForTest.class);
		Assert.assertTrue(check);
	}

}
