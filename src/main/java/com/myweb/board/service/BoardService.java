package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.boardVO;

public interface BoardService {
	public int regist(HttpServletRequest request, HttpServletResponse response);
	public ArrayList<boardVO> getList(HttpServletRequest request, HttpServletResponse response);
	public boardVO getContent(HttpServletRequest request, HttpServletResponse response);
	public int setUpdate(HttpServletRequest request,HttpServletResponse response);
	public void delete(HttpServletRequest request, HttpServletResponse response);
	public void hitUpdate(HttpServletRequest request, HttpServletResponse response);
}
