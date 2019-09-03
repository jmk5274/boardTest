package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;

public class BoardDaoImpl implements IBoardDao {
	private static IBoardDao dao;
	
	private BoardDaoImpl() { }
	
	public static IBoardDao getInstance() {
		if(dao==null) dao = new BoardDaoImpl();
		
		return dao;
	}

	@Override
	public List<BoardVo> getBoardList(SqlSession sqlSession) {
		return sqlSession.selectList("board.getBoardList");
	}

	@Override
	public int insertBoard(SqlSession sqlSession, BoardVo bvo) {
		return sqlSession.insert("board.insertBoard", bvo);
	}

	@Override
	public int updateBoard(SqlSession sqlSession, BoardVo bvo) {
		return sqlSession.update("board.updateBoard", bvo);
	}

	@Override
	public BoardVo getBoard(SqlSession sqlSession, int boardnum) {
		return sqlSession.selectOne("board.getBoard", boardnum);
	}
	
	
}
