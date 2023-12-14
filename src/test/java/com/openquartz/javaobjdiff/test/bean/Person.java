package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.annotation.DiffBean;
import com.openquartz.javaobjdiff.annotation.DiffFormat;
import com.openquartz.javaobjdiff.formatter.DateTimeDiffFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class Person {

    private String name;

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
}
