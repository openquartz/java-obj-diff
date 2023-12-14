package com.openquartz.javaobjdiff.test.bean;

import java.math.BigDecimal;

public class Packet {

    private String name;

    private BigDecimal money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Packet{" +
            "name='" + name + '\'' +
            ", money=" + money +
            '}';
    }
}
