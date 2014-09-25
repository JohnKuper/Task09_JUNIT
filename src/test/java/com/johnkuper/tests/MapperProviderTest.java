package com.johnkuper.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.johnkuper.annotations.ClassAnnotation;
import com.johnkuper.annotations.FieldAnnotation;
import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.datafortests.CreditCardWithoutFields;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.mapper.FieldsContainer;
import com.johnkuper.mapper.MapperProvider;
import com.johnkuper.mapper.ValidatorOfClasses;

@RunWith(MockitoJUnitRunner.class)
public class MapperProviderTest {

	MapperProvider provider;

	@Mock
	ValidatorOfClasses validatorMock;

	@Test
	public void verify_Mapping_Model_For_True_Validation_Should_Return_True()
			throws OwnMapperException {

		Mockito.when(
				validatorMock.isAnnotationNameEqualWithOutputClassName(
						CreditCard.class, BillingDetails.class)).thenReturn(
				true);
		Mockito.when(
				validatorMock.isBothClassesContainFields(CreditCard.class,
						BillingDetails.class)).thenReturn(true);
		provider = new MapperProvider(CreditCard.class, BillingDetails.class,
				validatorMock);

		boolean check = provider.verifyMappingModel();
		Assert.assertTrue(check);

	}

	@Test
	public void verify_Mapping_Model_For_False_Validation_Should_Return_False()
			throws OwnMapperException {

		Mockito.when(
				validatorMock.isAnnotationNameEqualWithOutputClassName(
						CreditCard.class, BillingDetails.class)).thenReturn(
				true);
		Mockito.when(
				validatorMock.isBothClassesContainFields(
						CreditCardWithoutFields.class, BillingDetails.class))
				.thenReturn(false);
		provider = new MapperProvider(CreditCard.class, BillingDetails.class,
				validatorMock);

		boolean check = provider.verifyMappingModel();
		Assert.assertFalse(check);

	}

	@Test(expected = OwnMapperException.class)
	public void no_Such_Field_Should_Thrown_Exception()
			throws OwnMapperException {

		class DestiClass {
		}
		@ClassAnnotation(name = "DestiClass")
		class SourceClass {
			@FieldAnnotation(name = "NoSuchField")
			public String str;
		}

		Mockito.when(
				validatorMock.isAnnotationNameEqualWithOutputClassName(
						SourceClass.class, DestiClass.class)).thenReturn(true);
		Mockito.when(
				validatorMock.isBothClassesContainFields(SourceClass.class,
						DestiClass.class)).thenReturn(true);

		provider = new MapperProvider(SourceClass.class, DestiClass.class,
				validatorMock);

		provider.getFieldsForMapping();

	}

	@Test
	public void get_Fields_For_Mapping_Should_Not_Collect_Fields_With_Different_Types()
			throws OwnMapperException {

		class DestiClass {
			@SuppressWarnings("unused")
			public int someint;
		}
		@ClassAnnotation(name = "DestiClass")
		class SourceClass {
			@FieldAnnotation(name = "someint")
			public String str;
		}

		Mockito.when(
				validatorMock.isAnnotationNameEqualWithOutputClassName(
						SourceClass.class, DestiClass.class)).thenReturn(true);
		Mockito.when(
				validatorMock.isBothClassesContainFields(SourceClass.class,
						DestiClass.class)).thenReturn(true);

		provider = new MapperProvider(SourceClass.class, DestiClass.class,
				validatorMock);

		List<FieldsContainer> testContainer = provider.getFieldsForMapping();
		Assert.assertEquals(0, testContainer.size());
	}

	@Test
	public void get_Fields_For_Mapping_Should_Collect_Fields_With_Equals_Types()
			throws OwnMapperException {

		class DestiClass {
			@SuppressWarnings("unused")
			public String someint;
		}
		@ClassAnnotation(name = "DestiClass")
		class SourceClass {
			@FieldAnnotation(name = "someint")
			public String str;
		}

		Mockito.when(
				validatorMock.isAnnotationNameEqualWithOutputClassName(
						SourceClass.class, DestiClass.class)).thenReturn(true);
		Mockito.when(
				validatorMock.isBothClassesContainFields(SourceClass.class,
						DestiClass.class)).thenReturn(true);

		provider = new MapperProvider(SourceClass.class, DestiClass.class,
				validatorMock);

		List<FieldsContainer> testContainer = provider.getFieldsForMapping();
		Assert.assertEquals(1, testContainer.size());
	}

}
