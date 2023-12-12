package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.annotation.DiffAlias;

public class Address {

    private String country;

    @DiffAlias(alias = "城市")
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
            "country='" + country + '\'' +
            ", city='" + city + '\'' +
            '}';
    }
}
