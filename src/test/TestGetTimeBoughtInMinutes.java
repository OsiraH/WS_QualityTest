package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

class TestGetTimeBoughtInMinutes {

	ControlPayStation payStationCTR;
	
	@BeforeEach
	void setUp() throws Exception {
		payStationCTR = new ControlPayStation();
	}

	@Test
	void test() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 20;	// In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		payStationCTR.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, payStationCTR.readDisplay(), "Should display 20 for 50 DKK");

	}

}
