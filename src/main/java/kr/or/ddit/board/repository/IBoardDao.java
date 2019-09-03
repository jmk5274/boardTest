package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.post.model.AttachedfileVo;

public interface IBoardDao {
	
	public List<BoardVo> getBoardList(SqlSession sqlSession);
	
	public int insertBoard(SqlSession sqlSession, BoardVo bvo);
	
	public int updateBoard(SqlSession sqlSession, BoardVo bvo);
	
	public BoardVo getBoard(SqlSession sqlSession, int boardnum);
	
}
