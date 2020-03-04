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
import org.junit.jupiter.api.Test;

import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.DatabasePPrice;
import modellayer.PBuy;
import modellayer.PPayStation;
import modellayer.PPrice;

public class TestInsertParkingBuy {
	
	DBConnection con = null;
	DatabasePBuy dbpb = null;
	int result = -1;

	@Before
	public void setUp() {
		con = DBConnection.getInstance();
		DatabasePBuy dbpb = new DatabasePBuy();
	}
	
	@Test
	public void validVariablesInserted() {
		result = -1;
		PBuy pb = new PBuy();
		dbpb = new DatabasePBuy();
		pb.setAssociatedPaystation(new PPayStation(10, "Aalborg"));
		pb.setBuyTime(LocalDate.now());
		pb.setId(1);
		pb.setPayedCentAmount(10.00);
		pb.setParkingDuration(4);
		try {
		result = dbpb.insertParkingBuy(pb);
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
		}
		assertEquals(1, result);
	}
	
	@AfterClass
	public static void cleanUpWhenFinish() {
		DBConnection.closeConnection();
	}	

}
