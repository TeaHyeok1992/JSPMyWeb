package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.userVO;
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
		//화면이동의 기본값은 forward 형식이 되야합니다.
		if(path.equals("/user/join.user")) {
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		}else if(path.equals("/user/login.user")) {
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		}else if(path.equals("/user/joinForm.user")) {
			
			int result=service.join(request, response);
			if(result==1) {
				request.setAttribute("msg", "아이디가 중복되었습니다.");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else {
				response.sendRedirect("login.user");//다시 controller 를 거치게 설계한다
			}
			
		}else if(path.equals("/user/loginForm.user")) {
			userVO vo=service.login(request, response);
			if(vo!=null) {//로그인 성공
				HttpSession session=request.getSession();
				//서블릿에서는 request.getSession()으로 세션기능 사용 가능합니다.
				session.setAttribute("user_id", vo.getId());
				session.setAttribute("user_name", vo.getName());
				response.sendRedirect(request.getContextPath());
				// 해당 입력시 홈화면으로 갑니다.
			}else {//로그인 실패
				request.setAttribute("msg", "아이디 및 비밀번호를 확인하세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
		}else if (path.equals("/user/logout.user")) {
			HttpSession session=request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}else if(path.equals("/user/mypage.user")) {
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		}else if (path.equals("/user/update.user")) {
			userVO vo=service.getUserInfo(request, response);
			request.setAttribute("user_id", vo.getId());
			request.setAttribute("user_name", vo.getName());
			request.setAttribute("user_gender", vo.getGender());
			request.setAttribute("user_email", vo.getEmail());
			request.setAttribute("user_address", vo.getAddress());
		
			request.getRequestDispatcher("user_update.jsp").forward(request, response);
			
		}else if(path.equals("/user/updateForm.user")) {
			//0이면 실패 1이면 성공
			int result=service.update(request,response);
			
			if(result==1) {
				//브라우저 화면에 직접 팝업띄우고 넘겨주기
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보 수정이 완료 되었습니다.');");
				out.println("location.href='mypage.user';");
				out.println("</script>");
				
			}else {
				response.sendRedirect("mypage.user");
			}
		}
		
		
		
	}

}
