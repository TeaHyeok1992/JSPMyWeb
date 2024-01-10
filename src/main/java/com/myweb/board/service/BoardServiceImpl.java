package com.myweb.board.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.boardDAO;
import com.myweb.board.model.boardVO;

public class BoardServiceImpl implements BoardService{

	private boardDAO dao=boardDAO.getInstance();
	public int regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer=request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		int result=dao.regist(writer, title, content);
		
		
		return result;
		
	}
	
	public ArrayList<boardVO> getList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<boardVO> list=dao.getList();
		
		return list;
	}

	
	public boardVO getContent(HttpServletRequest request, HttpServletResponse response) {
		String bno=request.getParameter("bno");
		boardVO vo=dao.getContent(bno);
		return vo;
	}

	
	public int setUpdate(HttpServletRequest request, HttpServletResponse response) {
		String bno=request.getParameter("bno");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		int result=dao.setUpdate(bno,writer,content,title);
		
		return result;
	}

	
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String bno=request.getParameter("bno");
		dao.delete(bno);
		
	}

	
	public void hitUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		//Cookie coo = new Cookie(key,value);
		//coo.setMaxAge(30);
		//response.addCookie(coo);//쿠키생성방법
		
		String bno=request.getParameter("bno");
		String cooValue ="";
		boolean flag=true;//if구문의 실행여부
		Cookie[]cook=request.getCookies();
		if(cook!=null) {
			for(Cookie c:cook) {
				if(c.getName().equals("hit")) {
					cooValue=c.getValue();
					
					if(c.getValue().contains(bno)) {
						flag=false;
					}
				}
			}
		}
		if(flag==true) {
			dao.hitUpdate(bno);
			cooValue += bno+"-";
		}
		
		Cookie coo = new Cookie("hit",cooValue);
		coo.setMaxAge(30);
		response.addCookie(coo);
		
		
		
		
	}

	
	
	
	
	
}
