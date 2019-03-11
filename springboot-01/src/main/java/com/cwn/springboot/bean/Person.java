package com.cwn.springboot.bean;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "person")
@Component
public class Person {

    private  Integer personid;
    private  String personname;



    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", personname='" + personname + '\'' +
                '}';
    }



    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

}
