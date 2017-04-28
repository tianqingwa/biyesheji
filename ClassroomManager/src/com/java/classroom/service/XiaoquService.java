package com.java.classroom.service;

import java.util.List;

import com.java.classroom.bean.Xiaoqu;

public interface XiaoquService {

	List<Xiaoqu> findAll();

	int save(Xiaoqu xiaoqu);

	int update(Xiaoqu xiaoqu, String id);

	int deleteByIds(String delIds);

	int deleteById(Integer id);

}
