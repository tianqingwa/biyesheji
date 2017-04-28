package com.java.classroom.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Xiaoqu {
    private Integer id;

    private String xiaoquaddr;

    private Integer studentcount;

    private Date createdate;

    private Date updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXiaoquaddr() {
        return xiaoquaddr;
    }

    public void setXiaoquaddr(String xiaoquaddr) {
        this.xiaoquaddr = xiaoquaddr == null ? null : xiaoquaddr.trim();
    }

    public Integer getStudentcount() {
        return studentcount;
    }

    public void setStudentcount(Integer studentcount) {
        this.studentcount = studentcount;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public String getCreatetime(){
    	return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(createdate);
    }
    public String getUpdatetime(){
    	return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(updatedate);
    }
}