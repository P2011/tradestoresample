package com.trade.tradestore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import com.trade.tradestore.controller.TradestoreController;
import com.trade.tradestore.model.Trade;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest

class TradestoreApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private TradestoreController tradestoreController;
	

	@Test
	void test_getSuccess() {
		tradestoreController.updateTrades(formData("T1", 1, "B1", LocalDate.of(2021, 10, 31)));
		List<Trade> tradeList = tradestoreController.getAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeId());
	}

	
	@Test 
	void test_getVersionnew() {
		tradestoreController.updateTrades(formData("T1", 1, "B1", LocalDate.of(2021, 10, 31)));  
		List<Trade> tradeList = tradestoreController.getAllTrades(); 
		Assertions.assertEquals(1,tradeList.get(0).getVersion());
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeId());

				try {
					tradestoreController.updateTrades(formData("T1", 2, "B1", LocalDate.of(2021, 10, 31))); 
				}catch (Exception e){
					Assertions.assertEquals("Version is lesser than the existing version. Hence rejecting.", e.getMessage());
				}
				List<Trade> updatedList =tradestoreController.getAllTrades();
				Assertions.assertEquals(2, updatedList.size());
				Assertions.assertEquals("T1",updatedList.get(0).getTradeId());
				Assertions.assertEquals(2,updatedList.get(1).getVersion());
	}

	@Test 
	void test_prevVersion() {
				try {
					tradestoreController.updateTrades(formData("T1", 1, "B1", LocalDate.of(2021, 10, 31))); 
				}catch (Exception e){
					Assertions.assertEquals("Version is lesser than the existing version. Hence rejecting.", e.getMessage());
				}
				List<Trade> updatedList =tradestoreController.getAllTrades();
				Assertions.assertEquals(2, updatedList.size());
	}
	 
	
	@Test
	void test_pastMaturityDate() {
		try {
			tradestoreController.updateTrades(formData("T2", 1, "B1", LocalDate.of(2010, 10, 31)));
		}catch (Exception ie) {
			Assertions.assertEquals("Maturity date is lesser than today. Hence rejecting the record", ie.getMessage());
		}
	}
	
	private Trade formData(String tradeId,int version,String bookId, LocalDate  maturityDate){
		Trade trade = new Trade();
		trade.setTradeId(tradeId);
		trade.setBookId(bookId);
		trade.setVersion(version);
		trade.setCounterParty("CP-" + version);
		trade.setMaturityDate(maturityDate);
		trade.setExpiredFlag("N");
		trade.setCreatedDate(LocalDate.now());
		return trade;
	}

}


