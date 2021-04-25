package com.trade.tradestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trade.tradestore.model.Trade;
import com.trade.tradestore.model.TradeId;

@Repository
public interface TradestoreRepository extends JpaRepository<Trade,TradeId> {

}
