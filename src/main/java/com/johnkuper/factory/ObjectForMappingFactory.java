package com.johnkuper.factory;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;

/**
 * Simple factory for generating test data.
 * 
 * @author Dmitriy_Korobeinikov
 *
 */

public class ObjectForMappingFactory {

	/**
	 * @return CreditCard with test data
	 */
	public CreditCard getCreditCard() {

		CreditCard cc = new CreditCard();
		cc.setNumber("4276680045129865");
		cc.setCardType("Visa");
		cc.setExpMonth((byte) 10);
		cc.setExpYear(2016);
		

		return cc;
	}

	public BillingDetails getBillingDetails() {
		return new BillingDetails();
	}

}
