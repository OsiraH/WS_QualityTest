package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.IDbPPrice;
import databaselayer.DatabasePPrice;
import modellayer.PPrice;

public class ControlPrice {
	
	private IDbPPrice dbPrice;
	
	public ControlPrice() {
		this.dbPrice = new DatabasePPrice();
	}
	
	public PPrice getCurrentPrice() {
		
		PPrice currentPrice = new PPrice(); //hardcoded
		
		return currentPrice;
	}
	
	// Return type changed to int by testers
	public int getPriceRemote(int zoneId) throws DatabaseLayerException {
		
		// Get price from Parkingsystem DB
		int readPrice = dbPrice.getPriceByZoneId(zoneId);
		//
		return readPrice;
	}

}
