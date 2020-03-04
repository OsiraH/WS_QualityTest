package databaselayer;

import java.sql.*;
import java.time.LocalDate;
import java.lang.NullPointerException;

import modellayer.PBuy;
import modellayer.PPayStation;

public class DatabasePBuy implements IDbPBuy {

	// example using embedded SQL
	public int insertParkingBuy(PBuy parkingBuy) throws DatabaseLayerException {
		int insertedKey = 1;
		
		java.sql.Date sqldate = java.sql.Date.valueOf(parkingBuy.getBuyTime());
		PPayStation payStation = parkingBuy.getAssociatedPaystation();
		
		int parkingDuration = payStation.getTimeBoughtInMinutes();
		double payedCentAmount = payStation.getAmount();

		Connection con = DBConnection.getInstance().getDBcon();

		String baseInsert = "INSERT INTO PBuy (buyTime, duration, payedAmount, pPaystation_id)";
		baseInsert += "\nVALUES (" + sqldate + ", " + parkingDuration + ", " + payedCentAmount + ", " + payStation.getId() + ")";
		
		System.out.println(baseInsert);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			stmt.executeQuery(baseInsert);
	
			ResultSet rs = stmt.getGeneratedKeys();
		    if (rs.getRow() > 1 && rs.next()) {
		    	insertedKey = rs.getInt(1);
		    }
		    stmt.close();
		} catch (SQLException ex) {
			//Note from testers: the commented catch methods are preventing the correct insertion of data. Need to investigate further.
//			insertedKey = -1;
//			DatabaseLayerException dle = new DatabaseLayerException("Error inserting data");
//			dle.setStackTrace(ex.getStackTrace());
//			throw dle;
//		} catch (NullPointerException ex) {
//			insertedKey = -2;
//			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
//			dle.setStackTrace(ex.getStackTrace());
//			throw dle;
//		} catch (Exception ex) {
//			insertedKey = -3;
//			DatabaseLayerException dle = new DatabaseLayerException("Data not inserted! Technical error");
//			dle.setStackTrace(ex.getStackTrace());
//			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
		
		System.out.println("Inserted key: " + insertedKey);
		
		return insertedKey;

	}

	// example using prepared stmt
	public int deleteParkingBuy(PBuy parkingBuy) throws DatabaseLayerException {
		int numRowsDeleted = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		String baseDelete = "delete from PBuy where id = ?";
		System.out.println(baseDelete);

		try {
			con = DBConnection.getInstance().getDBcon();
			pstmt = con.prepareStatement(baseDelete);
			pstmt.setLong(1, parkingBuy.getId());
			numRowsDeleted = pstmt.executeUpdate();
		} catch (SQLException ex) {
			numRowsDeleted = -1;
			DatabaseLayerException dle = new DatabaseLayerException("Error deleting data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			numRowsDeleted = -2;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			numRowsDeleted = -3;
			DatabaseLayerException dle = new DatabaseLayerException("Data not deleted! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
		
		return numRowsDeleted;
	}

}
