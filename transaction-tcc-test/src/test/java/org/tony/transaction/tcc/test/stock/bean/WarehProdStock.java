package org.tony.transaction.tcc.test.stock.bean;

import java.io.Serializable;

public class WarehProdStock implements Serializable {
    
    private static final long serialVersionUID = 6511126706816710469L;

    private String warehCode;
    
    private String prodCode;
    
    private Double qtyCommitted;

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

    public Double getQtyCommitted() {
        return qtyCommitted;
    }

    public void setQtyCommitted(Double qtyCommitted) {
        this.qtyCommitted = qtyCommitted;
    }
    
    
}
