package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.userDAO;

public class UserServiceImpl implements UserService{

	
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		
		userDAO dao=userDAO.getInstance();
		int result=dao.idCheck(id);//1이면 아이디중복 0이면 없음
		if(result==1) {
			return 1;
		}else {
			dao.userInsert(id, pw, name, email, address, gender);
			
			return 0;
		}
		
	}

}
