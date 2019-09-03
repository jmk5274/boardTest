package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardService {

	public List<BoardVo> getBoardList();
	
	public int insertBoard(BoardVo bvo);
	
	public int updateBoard(BoardVo bvo);
	
	public BoardVo getBoard(int boardnum);
}
