package com.java.classroom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.classroom.bean.Room;
import com.java.classroom.bean.RoomExample;
import com.java.classroom.dao.RoomMapper;
import com.java.classroom.dao.UserMapper;
import com.java.classroom.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomMapper roomMapper;

	@Override
	public List<Room> findAll() {
		RoomExample roomExample = new RoomExample();
		List<Room> rooms = roomMapper.selectByExample(roomExample);
		return rooms;
	}

	@Override
	public int roomAdd(Room room) {
		room.setCreatedate(new Date());
		room.setUpdatedate(new Date());
		return roomMapper.insert(room);
	}

	@Override
	public int updateRoom(Room room) {
		room.setUpdatedate(new Date());
		return roomMapper.updateByPrimaryKey(room);
	}

	@Override
	public int deleteByIds(String delIds) {
		String[] ids = delIds.split(",");
		for (String id : ids) {
			roomMapper.deleteByPrimaryKey(Integer.valueOf(id));
		}
		return 0;
	}

}
