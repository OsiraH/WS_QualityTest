package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.DatabasePPrice;
import modellayer.PBuy;
import modellayer.PPayStation;
import modellayer.PPrice;

public class TestInsertParkingBuy {
	
	static DBConnection con = null;
	static DatabasePBuy dbpb = null;
	int result;

	@BeforeAll
	public static void init() {
		con = DBConnection.getInstance();
		dbpb = new DatabasePBuy();
	}
	
	@Test
	@DisplayName ("TC01")
	public void validVariablesInserted() {
		PBuy pb = new PBuy();
		PPayStation pps = new PPayStation(10, "Aalborg");
		pps.setAmount(0.10);
		pb.setAssociatedPaystation(pps);
		pb.setBuyTime(LocalDate.now());
		try {
		result = dbpb.insertParkingBuy(pb);
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
		}
		System.out.println();dbpb.toString();
		assertEquals(1, result);
	}
	
	@AfterClass
	public static void cleanUpWhenFinish() {
		DBConnection.closeConnection();
	}	

}
