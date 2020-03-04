package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
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
	void testGet20MinFor50cents() throws IllegalCoinException {
		
		// Arrange
		int coinValue = 50; // Cents
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		int expectedParkingTime = 20;	// In minutes
		
		// Act
		payStationCTR.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, payStationCTR.readDisplay(), "Should display 20 min for 50 cents");

	}
	
	@Test
	void testGet107MinFor20dkk() throws IllegalCoinException {
		
		//Arrange 
		int coinValue = 20; // DKK
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		int expectedParkingTime = 107;	// In minutes
		
		// Act
		payStationCTR.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, payStationCTR.readDisplay(), "Should display 107 min for 20 DKK");
	}
	
	@Test	
	void  testMultipleCoinsInsertedConversion() throws IllegalCoinException {
		//Arrange
		ArrayList<Integer> coinValues = new ArrayList<>(
			    Arrays.asList(2, 5, 2, 1)); //DKK
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		int expectedParkingTime = 54; // In minutes
		//Act
		for(int i : coinValues) {
			payStationCTR.addPayment(i, coinCurrency, coinType);
		}
		
		//Assert
		assertEquals(expectedParkingTime, payStationCTR.readDisplay(), "Should display n min for 10 DKK");
	}
	
	@After
	public void cleanUp() {
		payStationCTR.setReady();
	}

}


