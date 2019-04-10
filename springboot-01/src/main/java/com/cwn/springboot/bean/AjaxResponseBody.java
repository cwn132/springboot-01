package com.cwn.springboot.bean;

import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;


public class AjaxResponseBody {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;


}
