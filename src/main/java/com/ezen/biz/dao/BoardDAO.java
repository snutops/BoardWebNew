package com.ezen.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ezen.biz.common.JDBCUtil;
import com.ezen.dto.BoardVO;

@Repository("boardDAO")
public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// sql 명령어 상수
	private static final String Board_INSERT = 
			"insert into board(seq, title, writer, content) values(board_seq.nextval, ?, ?, ?)";
	
	// 게시글 등록
	public void insertBoard(BoardVO board) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(Board_INSERT);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	private static final String Board_UPDATE = 
			"update set board set title=?, writer=?, content=?";
	
	public void updateBoard(BoardVO board) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(Board_UPDATE);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	private static final String Board_DELETE = 
			"delete from board where seq=?";
	
	public void deleteBoard(BoardVO board) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(Board_DELETE);
			pstmt.setInt(1, board.getSeq());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	private static final String Board_GET =
			"select * from board where seq=?";
	
	public BoardVO getBoard(BoardVO board) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		BoardVO bVo = new BoardVO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(Board_GET);
			pstmt.setInt(1, board.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo.setSeq(rs.getInt("seq"));
				bVo.setTitle(rs.getString("title"));
				bVo.setWriter(rs.getString("writer"));
				bVo.setContent(rs.getString("content"));
				bVo.setRegDate(rs.getDate("regdate"));
				bVo.setCnt(rs.getInt("cnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return bVo;
	}
	
	private static final String Board_LIST=
			"select * from board order by seq desc";
	
	public List<BoardVO> getBoardList(){
		System.out.println("===> JDBC로 getBoardList() 기능 처리");

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(Board_LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setSeq(rs.getInt("seq"));
				bVo.setTitle(rs.getString("title"));
				bVo.setWriter(rs.getString("writer"));
				bVo.setContent(rs.getString("content"));
				bVo.setRegDate(rs.getDate("regDate"));
				bVo.setCnt(rs.getInt("cnt"));	
				
				boardList.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
}
