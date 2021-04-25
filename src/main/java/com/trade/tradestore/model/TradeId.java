package com.trade.tradestore.model;

import java.io.Serializable;

public class TradeId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String tradeId;
	private int version;
	
	public TradeId() {
		super();
	}

	public TradeId(String string, int i) {
		super();
		this.tradeId = string;
		this.version = i;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}
	
	  @Override
	    public int hashCode() {
	        final int rnd = 31;
	        int code = 1;
	        code = rnd * code + ((tradeId == null) ? 0 : tradeId.hashCode());
	        return code;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        TradeId other = (TradeId) obj;
	        if (tradeId == null) {
	            if (other.tradeId != null)
	                return false;
	        } else if (!tradeId.equals(other.tradeId))
	            return false;
	       if (!(version == other.version))
	            return false;
	        return true;
	    }

	@Override
	public String toString() {
		return "TradeId [tradeId=" + tradeId + ", version=" + version + "]";
	}

	
}

