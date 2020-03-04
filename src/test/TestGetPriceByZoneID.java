package test;

import static org.junit.Assert.*;

import org.junit.*;
import java.time.LocalDate;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestGetPriceByZoneID {



		DBConnection con = null;
		DatabasePPrice dbPPrice = null;
		int zoneID = -1;

		/** Fixture for pay station price update testing. */
		@Before
		public void setUp() {
			con = DBConnection.getInstance();
			dbPPrice = new DatabasePPrice();
		}



		@Test
		public void wasRetrievedPriceDatabaselayer() {
			zoneID = 2;
			int result = -1;
			
			try {
				result = dbPPrice.getPriceByZoneId(zoneID);
			} catch (DatabaseLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(25, result);
		}

			@AfterClass
			public static void cleanUpWhenFinish() {
				// 		
				// Act
					DBConnection.closeConnection();
				}
			}	


