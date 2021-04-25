package com.trade.tradestore.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.trade.tradestore.service.TradestoreService;

@Component
public class TradestoreScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(TradestoreScheduler.class);

	@Autowired
	TradestoreService tradestoreService;

	/**
	 * Scheduler task to run 30 mins to update the expired flag to "Y"
	 */
	@Scheduled(cron = "0 0/30 0 * * ?")
	public void runScheduler() {
		LOG.info("Running the scheduler");
		tradestoreService.updateExpiryFlag();
	}
}