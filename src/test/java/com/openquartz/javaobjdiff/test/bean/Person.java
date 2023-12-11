package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.annotation.DiffBean;

public class Person {

    private String name;

    private Integer age;

    @DiffBean
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
