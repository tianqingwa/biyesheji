package com.java.classroom.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.classroom.bean.Xiaoqu;
import com.java.classroom.service.XiaoquService;

@Controller
@RequestMapping("/classroom")
public class XiaoquController {
	@Autowired
	XiaoquService xiaoquService;

	@RequestMapping("/xiaoquList")
	@ResponseBody
	public List<Xiaoqu> getXiaoquList() {
		List<Xiaoqu> xiaoquList = xiaoquService.findAll();
		return xiaoquList;

	}

	@RequestMapping("saveXiaoqu")
	@ResponseBody
	public List<Xiaoqu> saveXiaoqu(Xiaoqu xiaoqu) {
		xiaoqu.setCreatedate(new Date());
		xiaoqu.setUpdatedate(new Date());
		xiaoquService.save(xiaoqu);
		return xiaoquService.findAll();
	}

	@RequestMapping("updateXiaoqu")
	@ResponseBody
	public List<Xiaoqu> updateXiaoqu(Xiaoqu xiaoqu, String id) {
		System.out.println(xiaoquService.update(xiaoqu, id));
		return xiaoquService.findAll();
	}

	@RequestMapping(value = "/deleteXiaoqu", params = "delIds")
	@ResponseBody
	public List<Xiaoqu> deleteXiaoqu(String delIds) {
		String[] ids = delIds.split(",");
		for (String id : ids) {
			xiaoquService.deleteById(Integer.valueOf(id));
		}
		return xiaoquService.findAll();
	}
}
