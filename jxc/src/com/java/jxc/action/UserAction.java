/**
 * 
 */
package com.java.jxc.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java.jxc.model.PageBean;
import com.java.jxc.model.User;
import com.java.jxc.service.UserService;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;
import com.java.jxc.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;



/**
 * 管理员模板控制实现
 */

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	private String page; 
	private String rows;
	private String s_username;	
	private User user;
	private String delIds;
	private String id;
	private List<User> users;
	private String error;
	private String imageCode;
	private String old_password;
	
	UserService userService = new UserService();
	HttpServletRequest zrequest = ServletActionContext.getRequest();
	HttpSession session;

	
	public void setServletRequest(HttpServletRequest request) {
		this.zrequest = request;
		//获取Session
		session = request.getSession();
	}
	
	
	
	public String index() {
		
		if(StringUtil.isEmpty(user.getUsername()) || StringUtil.isEmpty(user.getPassword())){
			error = "用户名或密码为空！";
			return ERROR;
		}
		if(StringUtil.isEmpty(imageCode)) {
			error = "验证码为空！";
			return ERROR;
		}
		if(!imageCode.equals(session.getAttribute("sRand"))) {
			error = "验证码错误！";
			return ERROR;
		}
		
		User currentuser = userService.login(user);
		
		if(currentuser == null) {
			error = "用户名或密码错误！";
		 	return ERROR;
		} else {
			session = zrequest.getSession();
			session.setAttribute("currentuser", currentuser);
			return SUCCESS;
		}
	}
	
	public String logout() {
		return "logout";
	}
	
	@Override
	public String execute() throws Exception {
		int total = 0;  //总数量
		
		PageBean pageBean = new PageBean (Integer.parseInt(page), Integer.parseInt(rows));
		User user = (User)session.getAttribute("currentuser");
		
		total = userService.getTotal(user, s_username);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JsonUtil.formatRsToJsonArray(userService.queryForAll(user, pageBean, s_username));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		
		return null;
	}

	
	public String delete()throws Exception{
			JSONObject result=new JSONObject();
			int delNums=userService.delete(delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String save()throws Exception{
		int saveNums=0;
		JSONObject result=new JSONObject();
		if(StringUtil.isNotEmpty(id)){
			user.setId(Integer.parseInt(id));
		    saveNums = userService.update(user);
		} else {
			saveNums = userService.insert(user);
		} 
		if(saveNums>0){
			result.put("success", "true");
		}else{
			result.put("success", "true");
			result.put("errorMsg", "保存失败");
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	//===============GET,SET====================
	
	public User getUser() {
		return user;
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getS_username() {
		return s_username;
	}

	public void setS_username(String s_username) {
		this.s_username = s_username;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	
}

