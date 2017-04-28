package com.java.classroom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.classroom.bean.User;
import com.java.classroom.bean.UserExample;
import com.java.classroom.bean.UserExample.Criteria;
import com.java.classroom.dao.UserMapper;
import com.java.classroom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;

	@Override
	public User checkUserLogin(User user) {
		UserExample example = new UserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andPasswordEqualTo(user.getPassword());
		createCriteria.andUsernameEqualTo(user.getUsername());

		return mapper.selectByExample(example).get(0);
	}

	@Override
	public int userAdd(User user) {
		
		
		return mapper.insert(user);
	}

}
