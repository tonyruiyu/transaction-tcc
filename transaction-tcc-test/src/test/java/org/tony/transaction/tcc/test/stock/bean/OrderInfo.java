package org.tony.transaction.tcc.test.stock.bean;

import java.io.Serializable;

import org.springframework.stereotype.Service;

public class OrderInfo implements Serializable{

    private static final long serialVersionUID = -5357342378029477505L;

    private String sn;

    private String citNumber;

    private String type;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCitNumber() {
        return citNumber;
    }

    public void setCitNumber(String citNumber) {
        this.citNumber = citNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
