package com.openquartz.javaobjdiff.test;

import com.openquartz.javaobjdiff.DiffUtils;
import com.openquartz.javaobjdiff.test.bean.Address;
import com.openquartz.javaobjdiff.test.bean.Packet;
import com.openquartz.javaobjdiff.test.bean.Person;
import com.openquartz.javaobjdiff.test.bean.Sex;
import com.openquartz.javaobjdiff.util.IteratorUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiffUtilTest {

    public static void main(String[] args)  {

        Address address1 = new Address();
        address1.setCity("shanghai");
        address1.setCountry("tangzhen");

        Address address2 = new Address();
        address2.setCity("beijing");
        address2.setCountry("daxing");

        Packet packet1 = new Packet();
        packet1.setMoney(new BigDecimal("1.0"));
        packet1.setName("LV");
        Packet packet2 = new Packet();
        packet2.setName("PT");
        packet2.setMoney(new BigDecimal("2.000"));

        Person person1 = new Person();
        person1.setAge(10);
        person1.setName("jack");
        person1.setSex(Sex.MAN);
        person1.setMobile("17000000056");
        person1.setEmail("wangtest1111@126.com");
        person1.setIdCard("321556199301152110");
        person1.setAddress(address1);
        person1.setBirthDate(LocalDateTime.now());
        person1.setFriendIdList(IteratorUtils.toList(1,2,3));
        person1.setPacketList(IteratorUtils.toList(packet1,packet2));


        Packet packet3 = new Packet();
        packet3.setName("PT");
        packet3.setMoney(new BigDecimal("2.0"));
        Person person2 = new Person();
        person2.setAge(11);
        person2.setName("tom");
        person2.setSex(Sex.WOMAN);
        person2.setMobile("17130000056");
        person2.setEmail("litest2211@126.com");
        person2.setIdCard("301556199301152110");
        person2.setAddress(address2);
        person2.setBirthDate(LocalDateTime.now().plusDays(-10));
        person2.setFriendIdList(IteratorUtils.toList(2,3,4));
        person2.setPacketList(IteratorUtils.toList(packet3));

        String diff = DiffUtils.diff(person1, person2);
        System.out.println(diff);

    }

}
