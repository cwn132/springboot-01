package com.cwn.springboot.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserVO  implements Serializable {

    @ApiModelProperty(value = "用户ID", required = true)
    private  Integer userid;
    private  Integer age;
    private  Integer sex;

    private  String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private  String lastname;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String  email;
    private  String  password;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersigninfo() {
        return usersigninfo;
    }

    public void setUsersigninfo(String usersigninfo) {
        this.usersigninfo = usersigninfo;
    }

    private  String username;

    private  String usersigninfo;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    private  String about;

}
