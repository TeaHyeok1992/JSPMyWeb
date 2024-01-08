package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.service.BoardServiceImpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardController() {
        super();
        
    }

	BoardServiceImpl service=new BoardServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);///
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);///
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///URL주소 분기 요청별 코드 작성
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String path=uri.substring(request.getContextPath().length());
		System.out.println(path);
		if(path.equals("/board/list.board")) {
			request.getRequestDispatcher("board_list.jsp").forward(request, response);
		}else if(path.equals("/board/write.board")) {
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
		}else if(path.equals("/board/registForm.board")) {
			int result=service.regist(request, response);
			if(result==1) {
				request.getRequestDispatcher("board_list.jsp").forward(request, response);
			}else {
				
			}
		}
		
	}
	
	

}
