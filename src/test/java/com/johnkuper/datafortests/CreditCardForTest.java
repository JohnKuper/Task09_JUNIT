package com.johnkuper.datafortests;

import com.johnkuper.annotations.ClassAnnotation;
import com.johnkuper.annotations.FieldAnnotation;

@ClassAnnotation(name = "BillingDetailsForTest")
public class CreditCardForTest {
	
	@FieldAnnotation(name = "CC_number")
	private String number;

}
