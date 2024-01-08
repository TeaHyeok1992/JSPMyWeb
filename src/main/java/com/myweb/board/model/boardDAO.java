package com.myweb.board.model;


import java.sql.Connection;

import java.sql.PreparedStatement;


import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class boardDAO {
	private static boardDAO instance = new boardDAO();
	private boardDAO() {
		try {
			//Connection pool 
			//Class.forName("oracle.jdbc.driver.OracleDriver");//context.xml에 적어 두었으므로 필요없게됨
			//초기설정값 얻어오는 객체 init
			InitialContext init = new InitialContext();
			
			dataSource=(DataSource)init.lookup("java:comp/env/jdbc/oracle");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static boardDAO getInstance() {
		return instance;
	}
//	private String url=JdbcUtil.url;
//	private String uid=JdbcUtil.uid;
//	private String upw=JdbcUtil.upw;
	private DataSource dataSource;
	/////////////////////////////////////////
	
	public int regist(String writer,String title,String content) {
		int result=0;
		String sql="Insert into BOARD(BNO,writer,title,content)values(board_SEQ.nextval,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataSource.getConnection();//바로 DB에 접근이 가능함
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	
	
	
	
	
}
