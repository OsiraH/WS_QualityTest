package modellayer;

import controllayer.ControlPrice;
import controllayer.IllegalCoinException;
import utility.Validation;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik Bærbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class PPayStation {
	
	// PayStation ident
	private int id;	
	// PayStaion model
	private String payStationModel;
	// Amount inserted in pay station. Renamed to add the currency to make it clearer.
	private double insertedAmountEUR = 0;
	//Added the following variable to keep track of the Damount inserted in DKK, in case we need to refund the amount.
	private double insertedAmountDKK = 0;
	private ControlPrice controlPrice;
	
	
	public PPayStation(int id, String payStationModel) {
		this.id = id;
		this.payStationModel = payStationModel;
		
		this.controlPrice = new ControlPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayStationModel() {
		return payStationModel;
	}

	public void setPayStationModel(String payStationModel) {
		this.payStationModel = payStationModel;
	}	
	
	public double getAmount() {
		return insertedAmountEUR;
	}
	
	public void setAmount(double amount) {
		this.insertedAmountEUR = amount;
	}
	
	public void addAmount(Coin coin, PPrice currentPrice) {
		
		Currency.ValidCurrency currency = coin.getCurrency();
		
		double valueInCent = 0;

		if (currency == Currency.ValidCurrency.DKK) {
			//PPrice nowPrice = controlPrice.getCurrentPrice();
			valueInCent = getDkkCoinValueInCent(coin, currentPrice);
		} else {
			valueInCent = getEuroCoinValueInCent(coin);
		}
		
		this.insertedAmountEUR += valueInCent;
	}
	
	public int getTimeBoughtInMinutes() {
		
		PPrice aPrice = controlPrice.getCurrentPrice();
		int timeBoughtInMinutes = 0;

		double timeBoughtInSeconds = this.insertedAmountEUR * aPrice.getParkingPrice();
		timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

		return timeBoughtInMinutes;
	}
	
	public void validateCoin(Coin coin) throws IllegalCoinException {
		
		Validation.validateCoin(coin);	
	}
	

	private double getEuroCoinValueInCent(Coin coin) {
		double valueInCent = 0;
		double coinValue = coin.getAmount();

		if (coin.getCoinType() == Currency.ValidCoinType.INTEGER) {
			valueInCent = coinValue * 100;
		} else {
			valueInCent = coinValue;
		}

		return valueInCent;
	}

	private double getDkkCoinValueInCent(Coin coin, PPrice price) {
		double valueInCent = 0;
		Currency.ValidCoinType coinType = coin.getCoinType();
		double coinValue = coin.getAmount();

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = (coinValue * 100) / price.getExchangeEuroDkk();
		} else {
			valueInCent = coinValue / price.getExchangeEuroDkk();
		}

		return valueInCent;
	}	
	
}
