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
	//회원가입기능
	public void userInsert(String id, String pw,String name,String email,String address, String gender) {
		
		String sql="Insert into Users(id,pw,name,email,address,gender,regdate)values (?,?,?,?,?,?,SYSDATE)";
				
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
	
	public userVO login(String id,String pw) {
		userVO vo =null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs  = null;
		
		String sql="select * FROM Users where id=? and pw=?";
		
		try {
			conn=DriverManager.getConnection(url,uid,upw);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				vo=new userVO();
				vo.setId(id);
				vo.setName(rs.getString("Name"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return vo;
	}
	
	//로그인기능
	
	public userVO getUser(String id) {
		userVO vo = null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		String sql="select * from users where id=?";
		
		try {
			conn=DriverManager.getConnection(url,uid,upw);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo= new userVO();
				vo.setId(id);
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));
				vo.setGender(rs.getString("gender"));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	public int update(userVO vo) {
		int result=0;
		
		String sql="update users set pw=?,name=?,email=?,address=?,gender=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		System.out.println(vo.toString()+"!!");
		
		
		try {
			conn=DriverManager.getConnection(url, uid, upw);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getId());
			
			result=pstmt.executeUpdate();
			
			System.out.println(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
		
		return result;
	}
}
