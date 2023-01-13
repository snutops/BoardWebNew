package com.ezen.biz.common;

import java.sql.*;

public class JDBCUtil {
	
	public static Connection getConnection() {
	// 오라클 jdbc 드라이버를 메모리에 로드
	// 데이터베이스에 연결한 후에 연결정보(Connection)를 반환
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String uid = "spring_user";
		String pass = "ora123";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// Update, Delete 문 등의 연결 해지에 사용
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	// SELECT문 연결 해지에 사용
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
