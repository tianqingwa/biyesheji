package com.java.classroom.service;

import java.util.List;

import com.java.classroom.bean.Room;

public interface RoomService {

	List<Room> findAll();

	int roomAdd(Room room);

	int updateRoom(Room room);

	int deleteByIds(String delIds);

}
