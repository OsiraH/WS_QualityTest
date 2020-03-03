package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modellayer.PPayment;
import modellayer.PPrice;

class TestGetTimeBoughtInMinutes {

	PPayment pPayment;
	PPrice pPrice;
	
	@BeforeEach
	void setUp() throws Exception {
		pPayment = new PPayment(4);
		pPrice = new PPrice();
	}

	@Test
	void test() {
		int actual = pPayment.getTimeBoughtInMinutes();
		assertEquals(120, actual);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
}
