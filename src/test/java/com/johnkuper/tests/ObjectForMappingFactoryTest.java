package com.johnkuper.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.factory.ObjectForMappingFactory;

@RunWith(MockitoJUnitRunner.class)
public class ObjectForMappingFactoryTest {

	ObjectForMappingFactory factory;

	@Before
	public void setUp() {
		factory = new ObjectForMappingFactory();
	}

	@After
	public void cleanUp() {
		factory = null;
	}

	@Test
	public void test_GetCreditCard_Should_Return_CreditCard() {
		Object testcard = factory.getCreditCard();
		Assert.assertEquals(CreditCard.class, testcard.getClass());
	}

	@Test
	public void test_GetBillingDetails_Should_Return_BillingDetails() {
		Object testdetails = factory.getBillingDetails();
		Assert.assertEquals(BillingDetails.class, testdetails.getClass());
	}

}
