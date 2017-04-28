package com.java.classroom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.classroom.bean.Xiaoqu;
import com.java.classroom.bean.XiaoquExample;
import com.java.classroom.dao.XiaoquMapper;
import com.java.classroom.service.XiaoquService;

@Service
public class XiaoquServiceImpl implements XiaoquService {
	@Autowired
	XiaoquMapper xiaoquMapper;

	@Override
	public List<Xiaoqu> findAll() {
		// alt+ÏòÏÂ
		XiaoquExample example = new XiaoquExample();
		return xiaoquMapper.selectByExample(example);
	}

	@Override
	public int save(Xiaoqu xiaoqu) {
		return xiaoquMapper.insert(xiaoqu);
	}

	@Override
	public int update(Xiaoqu xiaoqu, String id) {
		Date createdate = xiaoquMapper.selectByPrimaryKey(Integer.valueOf(id))
				.getCreatedate();
		xiaoqu.setCreatedate(createdate);
		xiaoqu.setUpdatedate(new Date());
		return xiaoquMapper.updateByPrimaryKey(xiaoqu);
	}

	@Override
	public int deleteByIds(String delIds) {

		return xiaoquMapper.deleteByIds(delIds);
	}

	@Override
	public int deleteById(Integer id) {
		
		return xiaoquMapper.deleteByPrimaryKey(id);
	}

}
