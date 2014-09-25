package com.johnkuper.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.datafortests.BillingDetailsForTest;
import com.johnkuper.datafortests.CreditCardForTest;
import com.johnkuper.datafortests.CreditCardWithoutFields;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.mapper.FieldsContainer;
import com.johnkuper.mapper.ValidatorOfClasses;

@RunWith(MockitoJUnitRunner.class)
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

	@Test
	public void both_Classes_Contain_Fields_Should_Return_True() {

		boolean check = validator.isBothClassesContainFields(CreditCard.class,
				BillingDetails.class);
		Assert.assertTrue(check);
	}

	/*
	 * Very strange behavior of test. When first run it work correct, but second
	 * and more shouldfalse=true. It happends when I use 'Run As' with EclEmma. Looks like a bug. 
	 */
	@Test
	public void one_Class_Not_Contain_Fiedls_Should_Return_False() {

		boolean shouldfalse = validator.isBothClassesContainFields(
				CreditCardWithoutFields.class, BillingDetails.class);
		Assert.assertFalse(shouldfalse);
	}

}
