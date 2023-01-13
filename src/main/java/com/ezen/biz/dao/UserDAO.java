package com.ezen.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ezen.biz.common.JDBCUtil;
import com.ezen.dto.UserVO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET =
			"select * from users where id=? and password=?";
	
	public UserVO getUser(UserVO user) {
		System.out.println("===> getUser() 기능 처리");
		
		UserVO uVo = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setNString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				uVo = new UserVO();
				uVo.setId(rs.getString("id"));
				uVo.setPassword(rs.getString("password"));
				uVo.setName(rs.getString("name"));
				uVo.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return uVo;
	}
}
