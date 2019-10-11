package org.tony.transaction.tcc.test.stock.bean;

import java.io.Serializable;

public class UrUnitReservedStock implements Serializable{
    
    private static final long serialVersionUID = -3517658829231894095L;

    private String warehCode;
    
    private String prodCode;
    
    private String channelCode;
    
    private String type;
    
    private Double qtyCommit;
    
    private Double preQtyCommit;

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

    public Double getQtyCommit() {
        return qtyCommit;
    }

    public void setQtyCommit(Double qtyCommit) {
        this.qtyCommit = qtyCommit;
    }

    public Double getPreQtyCommit() {
        return preQtyCommit;
    }

    public void setPreQtyCommit(Double preQtyCommit) {
        this.preQtyCommit = preQtyCommit;
    }


    
    
    
}
