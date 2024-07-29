package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	
	// 생성자

	// 메소드 gs

	// 메소드 일반

	//DB연결 메소드
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//자원정리 메소드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	// 작가등록
	public int insertAuthor(String name, String desc) {
		int count = -1;

		this.getConnection(); 

		try {
			 
			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}// 작가등록

	
	
	// 작가삭제
	public int deleteAuthor(int authorId) {
		int count = -1;

		this.getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			// *바인딩 ?
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}// 작가삭제

	
	
	// 작가수정
	public int updateAuthor(int authorId, String name, String desc) {
		int count = -1;
		
		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, authorId);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}// 작가수정
	
	
	
	// 작가전체리스트
	public List<AuthorVo> selectAuthorAll() {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		this.getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " select 	author_id, ";
			query += "		    author_name, ";
			query += "          author_desc ";
			query += " from author ";
			query += " order by author_id asc ";

			// *바인딩
			pstmt = conn.prepareStatement(query);

			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {
				int id = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(id, name, desc);
				authorList.add(authorVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return authorList; // 리스트의 *주소를 리턴해준다

	}// 작가전체리스트

	// 작가한명
	public AuthorVo selectAuthor(int authorId) {
		AuthorVo authorVo = null;
		
		this.getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " select 	author_id, ";
			query += "		    author_name, ";
			query += "          author_desc ";
			query += " from author ";
			query += " where author_id = ? ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {
				int aId = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");

				authorVo = new AuthorVo(aId, name, desc);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return authorVo; // 리스트의 *주소를 리턴해준다

	}// 작가한명
}
