package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao {

	// 필드

	// 생성자

	// 메소드 gs

	// 메소드 일반

	// 작가등록
	public int insertAuthor(String name, String desc) {
		System.out.println(name);
		System.out.println(desc);
		System.out.println("저장 로직");

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

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

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

		return count;
	}// 작가등록

	// 작가삭제
	public int deleteAuthor(int id) {
		System.out.println("작가삭제");
		System.out.println(id);

		int count = -1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			//*sql문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			
			//*바인딩 ?
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			//*실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

		return count;
	}// 작가삭제

}
