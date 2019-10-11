package org.tony.transaction.tcc.test.stock.bean;

import java.io.Serializable;

public class WarehProdLockedStock implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8661311863414762756L;

    private String warehCode;
    
    private String prodCode;
    
    private String type;
    
    private Double lockedStock;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLockedStock() {
        return lockedStock;
    }

    public void setLockedStock(Double lockedStock) {
        this.lockedStock = lockedStock;
    }
    
    
    
    
}
