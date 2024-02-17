package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.annotation.DiffCompare;
import com.openquartz.javaobjdiff.comparator.BigDecimalEffectiveDiffComparator;
import java.math.BigDecimal;

public class Packet {

    private String name;

    @DiffCompare(using = BigDecimalEffectiveDiffComparator.class)
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
