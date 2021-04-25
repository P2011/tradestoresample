package com.trade.tradestore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.tradestore.model.Trade;
import com.trade.tradestore.model.TradeId;
import com.trade.tradestore.repository.TradestoreRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradestoreService {

	 private static final Logger LOG = LoggerFactory.getLogger(TradestoreService.class);

    @Autowired
    TradestoreRepository tradestoreRepository;
    
    LocalDate today = LocalDate.now() ;

    /**
     * Updates the data in the trade store db
     * @param trade
     * @return
     * @throws Exception 
     */
    public boolean updateTrade(Trade trade) throws Exception{
    	LOG.info("Update trade details");
    	Optional<Trade> optTrade = tradestoreRepository.findById(new TradeId(trade.getTradeId(), trade.getVersion()));
    	if(optTrade.isPresent()) {
    		Trade dbTrade = optTrade.get();
    		if(trade.getVersion() >= dbTrade.getVersion()) {
    			if (trade.getMaturityDate().isAfter(today)) {
    				tradestoreRepository.save(trade);
    				LOG.info("Updated trade details");
    				return true;
    			} else {
    				LOG.info("Maturity date is lesser than today. Hence rejecting.");
    				throw new Exception("Maturity date is lesser than today. Hence rejecting the record");
    			}
    		} else {
    			LOG.info("Version is lesser than the existing version. Hence rejecting.");
    			throw new Exception("Version is lesser than the existing version. Hence rejecting.");
    		}
    	} else {
    		
    		if (trade.getMaturityDate().isAfter(today)) {
    			trade.setCreatedDate(today);
        		tradestoreRepository.save(trade);
        		LOG.info("Added new trade details");
        		return true;
			} else {
				LOG.info("Maturity date is lesser than today. Hence rejecting.");
				throw new Exception("Maturity date is lesser than today. Hence rejecting the record");
			}
    		
    	}
    }

    /**
     * Get all the trade records
     */
    public List<Trade> findAll(){
       LOG.info("Retrieve trade details");
       List<Trade> tradeList = (List<Trade>) tradestoreRepository.findAll();
       LOG.info("trade details retrieved - {}" , tradeList.size());
       return tradeList;
    }
    
    /**
     * Updates the expiry flag of the trade record to "Y", if the maturity date has crossed the current date
     */
    public void updateExpiryFlag() {
    	List<Trade> tradeList = (List<Trade>) tradestoreRepository.findAll();
    	tradeList.forEach(trade -> {
                if (trade.getExpiredFlag().equals("N") && !(trade.getMaturityDate().isAfter(today))) {
                    trade.setExpiredFlag("Y");
                    tradestoreRepository.save(trade);
                    LOG.info("Updated the expiry flag for trade - {}", trade.toString());
                }
    			
    	});
    }
}
