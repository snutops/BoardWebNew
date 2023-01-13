package com.ezen.biz.board;

import java.util.List;

import com.ezen.dto.BoardVO;

public interface BoardService {

	// 게시글 등록
	void insertBoard(BoardVO board);

	void updateBoard(BoardVO board);

	void deleteBoard(BoardVO board);

	BoardVO getBoard(BoardVO board);

	List<BoardVO> getBoardList();

}