package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.boardDAO;

public class BoardServiceImpl implements BoardService{

	private boardDAO dao=boardDAO.getInstance();
	public int regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer=request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		int result=dao.regist(writer, title, content);
		
		
		return result;
		
	}
	
	
	
}
