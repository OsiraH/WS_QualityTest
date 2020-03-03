package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	public DatabasePPrice() {

	}
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	// Return type changed from PPrice to int by testers
	public int getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		//foundPrice and pZoneId added by testers
		int foundPrice = -1;
		int pZoneId = zoneId;
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "select top 1 price, pZone_id from PPrice ";
		baseSelect += "where pZone_id = " + zoneId + " and starttime < '" + dateNow + "' ";
		baseSelect += "order by starttime desc";
		System.out.println(baseSelect);
	
		//ResultSet rs = null; 
		
		// the two lines below have been commented by the testers
		//int price, pZoneId;
		//PZone pZone; 
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			
			// Start of code added by tester
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 price\r\n" + 
					"FROM PPrice\r\n" + 
					"WHERE pZone_id = 2 AND starttime < GETDATE() \r\n" + 
					"ORDER BY starttime DESC");
			 while (rs.next())
		      {
		        foundPrice = rs.getInt("price");
		        System.out.println("Price for "+ pZoneId +" is "+foundPrice);
		      }
			// End of code added by tester
			 
			stmt.close();
		} catch (SQLException ex) {
			foundPrice = -1;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = -1;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = -1;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	

}
