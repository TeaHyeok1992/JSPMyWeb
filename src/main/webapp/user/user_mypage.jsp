﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/Header.jsp" %>
		
		<div align="center" class="div_center">
		<h3>MY PAGE</h3>
		<hr>
		<p>
		(<b style="color: maroon;">${sessionScope.user_id }</b>)님의 정보를 관리합니다.
		</p>

		<a href="update.user">[회원 정보 변경]</a>&nbsp;&nbsp;

		<a href="delete.user">[회원 탈퇴]</a>
		</div>
		
<%@ include file="../include/Footer.jsp"%>