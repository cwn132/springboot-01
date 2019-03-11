package com.cwn.springboot.bean;

import java.util.Date;

public class Img {

    private Integer imgid  ;
    private Integer userid ;
    private String username;

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

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

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getImgtype() {
        return imgtype;
    }

    public void setImgtype(Integer imgtype) {
        this.imgtype = imgtype;
    }

    public Integer getImgcheckstatus() {
        return imgcheckstatus;
    }

    public void setImgcheckstatus(Integer imgcheckstatus) {
        this.imgcheckstatus = imgcheckstatus;
    }

    public Date getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Date deletedate) {
        this.deletedate = deletedate;
    }

    public Integer getDeletemanid() {
        return deletemanid;
    }

    public void setDeletemanid(Integer deletemanid) {
        this.deletemanid = deletemanid;
    }

    public String getDeletemanname() {
        return deletemanname;
    }

    public void setDeletemanname(String deletemanname) {
        this.deletemanname = deletemanname;
    }

    public Integer getImgstatus() {
        return imgstatus;
    }

    public void setImgstatus(Integer imgstatus) {
        this.imgstatus = imgstatus;
    }

    public Integer getScantype() {
        return scantype;
    }

    public void setScantype(Integer scantype) {
        this.scantype = scantype;
    }

    public Integer getImgnum() {
        return imgnum;
    }

    public void setImgnum(Integer imgnum) {
        this.imgnum = imgnum;
    }

    private String imgpath;
    private Date credate;
    private String memo;
    private Integer imgtype;
    private Integer imgcheckstatus;
    private Date deletedate;
    private Integer deletemanid;
    private String deletemanname;
    private Integer imgstatus;
    private Integer scantype;
    private Integer imgnum;

}
