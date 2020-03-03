package databaselayer;

import modellayer.PPrice;

public interface IDbPPrice {

	public PPrice getCurrentPrice();
    // Get Price by parking zone id
	// Return type changed from PPrice to int by testers
	public int getPriceByZoneId(int zoneId) throws DatabaseLayerException;
    
}
