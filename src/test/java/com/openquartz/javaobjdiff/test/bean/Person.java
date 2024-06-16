package com.openquartz.javaobjdiff.test.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.openquartz.javaobjdiff.annotation.DiffBean;
import com.openquartz.javaobjdiff.annotation.DiffCompare;
import com.openquartz.javaobjdiff.annotation.DiffFormat;
import com.openquartz.javaobjdiff.annotation.DiffIgnore;
import com.openquartz.javaobjdiff.formatter.DateTimeDiffFormatter;
import com.openquartz.javaobjdiff.formatter.datamask.EmailDataMask;
import com.openquartz.javaobjdiff.formatter.datamask.IdCardDataMask;
import com.openquartz.javaobjdiff.formatter.datamask.MobileDataMask;

public class Person {

    private String name;

    @DiffCompare(using = SelfEnumComparator.class)
    private Sex sex;

    @MobileDataMask
    private String mobile;

    @EmailDataMask
    private String email;

    @IdCardDataMask
    private String idCard;

    @DiffIgnore
    private Integer age;

    @DiffFormat(using = DateTimeDiffFormatter.class, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;

    @DiffBean
    private Address address;

    @DiffBean
    private List<Packet> packetList;

    @DiffBean
    private List<Integer> friendIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public List<Packet> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<Packet> packetList) {
        this.packetList = packetList;
    }

    public List<Integer> getFriendIdList() {
        return friendIdList;
    }

    public void setFriendIdList(List<Integer> friendIdList) {
        this.friendIdList = friendIdList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "{" +
            "name:'" + name + '\'' +
            ", sex:" + sex +
            ", mobile:'" + mobile + '\'' +
            ", email:'" + email + '\'' +
            ", idCard:'" + idCard + '\'' +
            ", age:" + age +
            ", birthDate:" + birthDate +
            ", address:" + address +
            ", packetList:" + packetList +
            ", friendIdList:" + friendIdList +
            '}';
    }
}
