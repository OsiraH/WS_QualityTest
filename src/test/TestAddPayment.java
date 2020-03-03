package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Coin;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;
import modellayer.PPayStation;

class TestAddPayment {

	private ControlPayStation ctrPayStation;
	private PPayStation payStation;
	private Coin oneEuroCoin;
	private Coin oneCentCoin;
	private Coin oneNokCoin;
	
	@BeforeAll
	void init(){
	ctrPayStation = new ControlPayStation();
	payStation = new PPayStation(2, "P-425M");
	oneEuroCoin = new Coin(1,ValidCurrency.EURO,ValidCoinType.INTEGER);
	oneCentCoin = new Coin(1,ValidCurrency.EURO, ValidCoinType.FRACTION);
	oneNokCoin = new Coin(1, ValidCurrency.NOK, ValidCoinType.INTEGER);
	}
	
	
	@Test
	@DisplayName ("addPayment - TC01")
	void validCoin() throws IllegalCoinException {
		payStation.validateCoin(oneEuroCoin);
	}
}

	
	

