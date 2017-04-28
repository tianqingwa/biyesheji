package com.java.classroom.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.classroom.bean.User;
import com.java.classroom.service.UserService;

@Controller
@RequestMapping("/classroom")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public String userLogin(User user, HttpSession session, Model model) {
		// System.out.println(user.get);
		if (user.getPassword() == null
				|| userService.checkUserLogin(user) == null)
			return "login";
		else {
			session.setAttribute("currentUser", user);
			return "main";
		}
		
	}
	@RequestMapping("/userAdd")
	public String userAdd(User user,Model respStr){
		userService.userAdd(user);
		return null;
	}

	@RequestMapping("/logout")
	public String userLogout() {
		return "login";
	}
}
