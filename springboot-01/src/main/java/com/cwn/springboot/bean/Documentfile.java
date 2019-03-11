package com.cwn.springboot.bean;

import java.util.Date;

public class Documentfile {

  private Integer  documentfileid;

    public Integer getDocumentfileid() {
        return documentfileid;
    }

    public void setDocumentfileid(Integer documentfileid) {
        this.documentfileid = documentfileid;
    }

    public String getDocumentfilename() {
        return documentfilename;
    }

    public void setDocumentfilename(String documentfilename) {
        this.documentfilename = documentfilename;
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

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    private String  documentfilename;
  private Integer  userid;
  private String   username;
  private Date credate;


}
