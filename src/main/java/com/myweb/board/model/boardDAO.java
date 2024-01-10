package com.myweb.board.model;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	
	public ArrayList<boardVO> getList(){
		
		ArrayList<boardVO> list = new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		String sql="select * from board order by bno desc";
		
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int bno=rs.getInt("BNO");
				String content=rs.getString("content");
				String writer=rs.getString("writer");
				int hit=rs.getInt("hit");
				Timestamp regdate=rs.getTimestamp("regdate");
				String title=rs.getString("title");
				
				boardVO vo = new boardVO(bno,writer,content,hit,regdate,title);
				list.add(vo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public boardVO getContent(String bno) {
		boardVO vo =new boardVO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from board where bno=?";
		
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int bno2=rs.getInt("BNO");
				String content=rs.getString("content");
				String writer=rs.getString("writer");
				int hit=rs.getInt("hit");
				Timestamp regdate=rs.getTimestamp("regdate");
				String title=rs.getString("title");
				vo.setBno(bno2);
				vo.setContent(content);
				vo.setHit(hit);
				vo.setRegdate(regdate);
				vo.setTitle(title);
				vo.setWriter(writer);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		
		
		return vo;
	}
	public int setUpdate(String bno, String writer, String content, String title) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update board set content=?, title=?, regdate=SYSDATE where writer=? AND bno=?";
		
			try {
				conn=dataSource.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setString(2, title);
				pstmt.setString(3, writer);
				pstmt.setString(4, bno);
				result=pstmt.executeUpdate();
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, null);
			}
		
		return result;
	}
	public void delete(String bno) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from board where bno=?";
		
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
	public void hitUpdate(String bno) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update board set hit=hit +1 where bno=?";
		
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	
}
