package org.tony.transaction.tcc.test.stock.bean;

import java.io.Serializable;

public class WarehLockedUnitStock implements Serializable {
    
    private static final long serialVersionUID = -5580355142282724663L;

    private String warehCode;
    
    private String prodCode;
    
    private String channelCode;
    
    private String type;
    
    private String rcvWarehCode;
    
    private Double lockedQty;
    
    private boolean isZd;

    public String getWarehCode() {
        return warehCode;
    }

    public void setWarehCode(String warehCode) {
        this.warehCode = warehCode;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRcvWarehCode() {
        return rcvWarehCode;
    }

    public void setRcvWarehCode(String rcvWarehCode) {
        this.rcvWarehCode = rcvWarehCode;
    }

    public Double getLockedQty() {
        return lockedQty;
    }

    public void setLockedQty(Double lockedQty) {
        this.lockedQty = lockedQty;
    }

    public boolean isZd() {
        return isZd;
    }

    public void setZd(boolean isZd) {
        this.isZd = isZd;
    }
    
    
}
