package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.annotation.DiffBean;
import com.openquartz.javaobjdiff.annotation.DiffFormat;
import com.openquartz.javaobjdiff.formatter.DateTimeDiffFormatter;
import java.time.LocalDateTime;

public class Person {

    private String name;

    private Integer age;

    @DiffFormat(using = DateTimeDiffFormatter.class, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;

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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }
}
