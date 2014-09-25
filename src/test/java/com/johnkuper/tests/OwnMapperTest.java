package com.johnkuper.tests;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.mapper.FieldsContainer;
import com.johnkuper.mapper.MapperProvider;
import com.johnkuper.mapper.OwnMapper;

@RunWith(MockitoJUnitRunner.class)
public class OwnMapperTest {

	OwnMapper mapper;

	@Mock
	CreditCard card;

	@Mock
	BillingDetails details;

	@Mock
	MapperProvider mProviderMock;

	@Test
	public void map_For_Null_Fields_List_Should_Return_Null_Target_Object()
			throws OwnMapperException {

		Mockito.when(mProviderMock.getFieldsForMapping()).thenReturn(null);

		mapper = new OwnMapper(card, details, mProviderMock);
		details = (BillingDetails) mapper.map();

		Assert.assertEquals(null, details);

	}

	@Test
	public void map_For_Not_Null_Fields_List_Should_Return_Target_Object()
			throws OwnMapperException, NoSuchFieldException, SecurityException {

		List<FieldsContainer> container = new ArrayList<>();
		Field f1 = CreditCard.class.getDeclaredField("number");
		Field f2 = BillingDetails.class.getDeclaredField("CC_number");
		container.add(new FieldsContainer(f1, f2));

		Mockito.when(mProviderMock.getFieldsForMapping()).thenReturn(container);

		mapper = new OwnMapper(card, details, mProviderMock);
		details = (BillingDetails) mapper.map();

		Assert.assertNotNull(details);

	}
	

}
