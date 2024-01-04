package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.myweb.util.JdbcUtil;

public class userDAO {
	
	private static userDAO instance = new userDAO();
	private userDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public static userDAO getInstance() {
		return instance;
	}
	private String url=JdbcUtil.url;
	private String uid=JdbcUtil.uid;
	private String upw=JdbcUtil.upw;
	/////////////////////////////////////////
	
	public int idCheck(String id) {
		int result=0;
		
		String sql="select * from users where id =?";
		
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			conn=DriverManager.getConnection(url,uid,upw);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//중복아이디존재한다.
				result=1;
			}else {//중복아이디 존재하지 않으므로 추가가능함
				result=0;
			}
		} catch (Exception e) {
			
			
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		
		return result;
	}
	public void userInsert(String id, String pw,String name,String email,String address, String gender) {
		
		String sql="Insert into Users(id,pw,name,email,address,gender,regdate)\r\n"
				+ "values (?,?,?,?,?,?,SYSDATE)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DriverManager.getConnection(url, uid, upw);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, address);
			pstmt.setString(6, gender);
			pstmt.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
	
	
	
	
}
