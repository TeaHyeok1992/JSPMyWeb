package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.userDAO;
import com.myweb.user.model.userVO;

public class UserServiceImpl implements UserService{

	private userDAO dao =userDAO.getInstance();
	
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		
		
		int result=dao.idCheck(id);//1이면 아이디중복 0이면 없음
		if(result==1) {
			return 1;
		}else {
			dao.userInsert(id, pw, name, email, address, gender);
			
			return 0;
		}
		
	}
		
	public userVO login(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		
		userVO vo=dao.login(id,pw);
		
		return vo;
		
	}
	
	public userVO getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("user_id");
		
		userVO vo=dao.getUser(id);
		
		return vo;
	}
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		
		userVO vo= new userVO(id,pw,name,email,address,gender,null);
		
		int result=dao.update(vo);
		
		return result;
	}
	
	
	

}
