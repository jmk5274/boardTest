package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;

public class PostDaoImpl implements IPostDao {

	private static IPostDao dao;
	
	private PostDaoImpl() { }
	
	public static IPostDao getInstance() {
		if(dao==null) dao = new PostDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<PostVo> getPostList(SqlSession sqlSession, Map map) {
		return sqlSession.selectList("post.getPostList", map);
	}

	@Override
	public PostVo selectPost(SqlSession sqlSession, int postNum) {
		return sqlSession.selectOne("post.selectPost", postNum);
	}

	@Override
	public List<CommentsVo> getCmtList(SqlSession sqlSession, int postNum) {
		return sqlSession.selectList("comments.getCmtList", postNum);
	}

	@Override
	public int insertCmt(SqlSession sqlSession, CommentsVo cvo) {
		return sqlSession.insert("comments.insertCmt", cvo);
	}

	@Override
	public int insertPost(SqlSession sqlSession, PostVo postVo) {
		return sqlSession.insert("post.insertPost", postVo);
	}
	
	@Override
	public int insertPost2(SqlSession sqlSession, PostVo postVo) {
		return sqlSession.insert("post.insertPost2", postVo);
	}

	@Override
	public int insertAtf(SqlSession sqlSession, AttachedfileVo atfVo) {
		return sqlSession.insert("atf.insertAtf", atfVo);
	}

	@Override
	public int getPostSeq(SqlSession sqlSession) {
		return sqlSession.selectOne("post.getPostSeq");
	}

	@Override
	public List<PostVo> allPostList(SqlSession sqlSession, int boardNum) {
		return sqlSession.selectList("post.allPostList", boardNum);
	}

	@Override
	public int deletePost(SqlSession sqlSession, PostVo postVo) {
		return sqlSession.update("post.deletePost", postVo);
	}

	@Override
	public int updatePost(SqlSession sqlSession, PostVo postVo) {
		return sqlSession.update("post.updatePost", postVo);
	}

	@Override
	public List<AttachedfileVo> getAttachedFile(SqlSession sqlSession, int postnum) {
		return sqlSession.selectList("atf.getAttachedFile", postnum);
	}

	@Override
	public int deleteAtf(SqlSession sqlSession, int atfnum) {
		return sqlSession.delete("atf.deleteAtf", atfnum);
	}

	@Override
	public int deleteCmt(SqlSession sqlSession, int cmtnum) {
		return sqlSession.update("comments.deleteCmt", cmtnum);
	}

	@Override
	public AttachedfileVo selectAtf(SqlSession sqlSession, int atfnum) {
		return sqlSession.selectOne("atf.selectAtf", atfnum);
	}
	
	
}
