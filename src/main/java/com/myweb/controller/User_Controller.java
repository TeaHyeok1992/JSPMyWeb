package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;


@WebServlet("*.user")
public class User_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public User_Controller() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);	
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("utf-8");
		//3. URL주소를 분기하여 요청별 코드를 만듬
		String uri=request.getRequestURI();
		String path=uri.substring(request.getContextPath().length());
		System.out.println(path);
		UserService service=new UserServiceImpl();
		//화면이동의 기본값은 forward형식이 되야합니다.
		if(path.equals("/user/join.user")) {
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		}else if(path.equals("/user/login.user")) {
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		}else if(path.equals("/user/joinForm.user")) {
			
			int result=service.join(request, response);
			
			System.out.println(result+"실행결과");
			
		}
		
		
		
	}

}
