package com.trade.tradestore.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.trade.tradestore.model.Trade;
import com.trade.tradestore.repository.TradestoreRepository;
import com.trade.tradestore.service.TradestoreService;

@RestController
public class TradestoreController {
	
	 private static final Logger log = LoggerFactory.getLogger(TradestoreController.class);

	@Autowired
	TradestoreRepository tradestoreRepository;
	
	@Autowired
	TradestoreService tradestoreService;

	/**
	 * Fetches all the trade details from store
	 */
	
	@GetMapping("/trades")
	public List<Trade> getAllTrades() {
		try {
			log.info("Trying to fetch all the trade details from store");
			return tradestoreService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	/**
	 * Creates or updates trade details to store
	 * @return
	 */
	@PostMapping("/trades")
	public boolean updateTrades(@RequestBody Trade trade) {
		try {
			System.out.println("Updating the trade details");
			return tradestoreService.updateTrade(trade);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	 
}
