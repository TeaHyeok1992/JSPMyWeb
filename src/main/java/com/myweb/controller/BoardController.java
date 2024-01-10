package com.myweb.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.boardVO;
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
			ArrayList<boardVO> list=service.getList(request, response);

			request.setAttribute("list", list);

			request.getRequestDispatcher("board_list.jsp").forward(request, response);
		}else if(path.equals("/board/write.board")) {
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
		}else if(path.equals("/board/registForm.board")) {
			int result=service.regist(request, response);
			if(result==1) {
				request.getRequestDispatcher("board_list.jsp").forward(request, response);
			}	
		}else if(path.equals("/board/content.board")){
			service.hitUpdate(request, response);
			boardVO vo=service.getContent(request, response);
			request.setAttribute("vo", vo);

			request.getRequestDispatcher("board_content.jsp").forward(request, response);
		}else if(path.equals("/board/modify.board")) {

			boardVO vo=service.getContent(request, response);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);

		}else if(path.equals("/board/update.board")) {
			int result2=service.setUpdate(request, response);

			if(result2==1) {
				response.sendRedirect("content.board?bno="+request.getParameter("bno"));
			}else {
				response.sendRedirect("modify.board?bno="+request.getParameter("bno"));
			}
		}else if(path.equals("/board/delete.board")) {
			service.delete(request,response);
			response.sendRedirect("list.board");//목록으로 회귀
		}
	}



}	


