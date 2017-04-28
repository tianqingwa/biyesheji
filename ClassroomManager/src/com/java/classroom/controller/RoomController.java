package com.java.classroom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.classroom.bean.Room;
import com.java.classroom.service.RoomService;

@RequestMapping("/classroom")
@Controller
public class RoomController {

	@Autowired
	RoomService roomService;

	@RequestMapping("/roomList")
	@ResponseBody
	public List<Room> getRoomList() {
		List<Room> rooms = roomService.findAll();
		return rooms;
	}

	@RequestMapping("/saveRoom")
	@ResponseBody
	public List<Room> saveRoom(Room room) {
		roomService.roomAdd(room);
		return roomService.findAll();
	}

	@RequestMapping("/updateRoom")
	@ResponseBody
	public List<Room> updateRoom(Room room) {
		roomService.updateRoom(room);
		return roomService.findAll();
	}

	@RequestMapping("/deleteRoom")
	@ResponseBody
	public List<Room> deleteRoom(String delIds, Model result) {
		roomService.deleteByIds(delIds);
		return roomService.findAll();
	}

	@RequestMapping("/roomtypeList")
	@ResponseBody
	public List<Map<String, String>> getRoomtypeList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomtype", "多媒体教室（小）");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("roomtype", "多媒体教室（大）");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("roomtype", "报告厅（大）");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("roomtype", "报告厅（小）");
		list.add(map);

		return list;
	}
}
