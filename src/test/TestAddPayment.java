package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import controllayer.ControlPayStation;
import controllayer.ControlPrice;
import controllayer.IllegalCoinException;
import modellayer.Coin;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;
import modellayer.PPayStation;

class TestAddPayment {

	private static ControlPayStation ctrPayStation;
	private static PPayStation payStation;
	private static ControlPrice ctrPrice;
	private static Coin oneEuroCoin;
	private static Coin oneCentCoin;
	private static Coin oneNokCoin;
	
	@BeforeAll
	static void init(){
	ctrPayStation = new ControlPayStation();
	payStation = new PPayStation(2, "P-425M");
	oneEuroCoin = new Coin(1,ValidCurrency.EURO,ValidCoinType.INTEGER);
	oneCentCoin = new Coin(1,ValidCurrency.EURO, ValidCoinType.FRACTION);
	oneNokCoin = new Coin(1, ValidCurrency.NOK, ValidCoinType.INTEGER);
	}
	
	
	@Test
	@DisplayName ("addPayment - TC01")
	void validCoinEuro() throws IllegalCoinException {
		payStation.validateCoin(oneEuroCoin);
	}
	
	@Test
	@DisplayName ("addPayment - TC02")
	void validCoinCent() throws IllegalCoinException {
		payStation.validateCoin(oneCentCoin);
	}
	
	@Test
	@DisplayName ("addPayment - TC03")
	void validCoinNok() {
	    Assertions.assertThrows(IllegalCoinException.class, () -> {
	    	payStation.validateCoin(oneNokCoin);
	      });
	}
	
	@Test
	@DisplayName("addPayment - TC04")
	void addPayment() throws IllegalCoinException {
		ctrPayStation.addPayment(50, ValidCurrency.EURO, ValidCoinType.FRACTION);
		ctrPayStation.addPayment(50, ValidCurrency.EURO, ValidCoinType.FRACTION);
		assertEquals(100,ctrPayStation.getAmount());
	}
	
	@Test
	@DisplayName("addPayment - TC05")
	void addPaymentNok() {
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ctrPayStation.addPayment(50, ValidCurrency.NOK, ValidCoinType.FRACTION);
		});
	}
	
	
}
