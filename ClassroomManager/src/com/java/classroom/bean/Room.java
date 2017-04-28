package com.java.classroom.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    private Integer id;

    private String roomnum;

    private String roomtype;

    private Date createdate;

    private Date updatedate;

    private Integer xiaoquid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum == null ? null : roomnum.trim();
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype == null ? null : roomtype.trim();
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

    public Integer getXiaoquid() {
        return xiaoquid;
    }

    public void setXiaoquid(Integer xiaoquid) {
        this.xiaoquid = xiaoquid;
    }
    
    public String getCreateTime(){
    	  return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(createdate);
    }
    public String getUpdateTime(){
    	return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(updatedate);
    }
}