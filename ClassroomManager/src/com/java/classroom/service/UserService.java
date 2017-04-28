package com.java.classroom.service;

import com.java.classroom.bean.User;




public interface UserService {

	User checkUserLogin(User user);

	int userAdd(User user);

}
