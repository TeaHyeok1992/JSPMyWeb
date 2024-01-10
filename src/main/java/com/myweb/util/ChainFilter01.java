package com.myweb.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;




//@WebFilter("/*")//필터실행조건 생성
public class ChainFilter01 implements Filter{//필터상속받기

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		
		
		
		
		System.out.println("chainfilter1 access");
		chain.doFilter(request, response);//다음필터로 연결하거나 Controller로 연결해줌
		
	}

}
