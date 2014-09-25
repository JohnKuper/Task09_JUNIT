package com.johnkuper.tester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;

public class MapperTester {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	public void outputDestiData(BillingDetails bildetails) {
		logger.debug("Summary output class data");
		Class<?> bilclazz = bildetails.getClass();
		String bilClassName = bilclazz.getSimpleName();
		logger.debug(bilClassName + " CC_number = " + bildetails.getCC_number());
		logger.debug(bilClassName + " CC_cardType = "
				+ bildetails.getCC_cardType());
		logger.debug(bilClassName + " CC_exp_month = "
				+ bildetails.getCC_exp_month());
		logger.debug(bilClassName + " CC_exp_year = "
				+ bildetails.getCC_exp_year());
		logger.debug("================================");
	}

	public void outputSourceData(CreditCard creditcard) {
		logger.debug("================================");
		logger.debug("Summary source class data");
		Class<?> creditclazz = creditcard.getClass();
		String creditClassName = creditclazz.getSimpleName();
		logger.debug(creditClassName + " number " + creditcard.getNumber());
		logger.debug(creditClassName + " cardType " + creditcard.getCardType());
		logger.debug(creditClassName + " expMonth " + creditcard.getExpMonth());
		logger.debug(creditClassName + " expYear " + creditcard.getExpYear());
		logger.debug("================================");
	}

}
