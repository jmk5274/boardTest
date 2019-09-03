package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.repository.IPostDao;
import kr.or.ddit.post.repository.PostDaoImpl;
import kr.or.ddit.util.MybatisUtil;

public class PostServiceImpl implements IPostService {

	private static IPostService serv;
	private IPostDao dao;
	
	private PostServiceImpl() { 
		dao = PostDaoImpl.getInstance();
	}
	
	public static IPostService getInstance() {
		if(serv==null) serv = new PostServiceImpl();
		return serv;
	}

	@Override
	public List<PostVo> getPostList(Map map) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<PostVo> list = dao.getPostList(sqlSession, map);
		sqlSession.close();
		return list;
	}

	@Override
	public Map<String, Object> selectPost(int postNum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		PostVo pvo = dao.selectPost(sqlSession, postNum);
		List<CommentsVo> cmtList = dao.getCmtList(sqlSession, postNum);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pvo", pvo);
		map.put("cmtList", cmtList);
		sqlSession.close();
		return map;
	}

	@Override
	public int insertCmt(CommentsVo cvo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.insertCmt(sqlSession, cvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int insertPost(Map map) {
		SqlSession sqlSession = MybatisUtil.getSession();

		PostVo pvo = (PostVo) map.get("pvo");
		
		int cnt = dao.insertPost(sqlSession, pvo);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int insertPost2(Map map) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int seq = dao.getPostSeq(sqlSession);
		PostVo pvo = (PostVo) map.get("pvo");
		
		int cnt = dao.insertPost2(sqlSession, pvo);
		
		sqlSession.commit();
		sqlSession.close();
		return 0;
	}

	@Override
	public List<PostVo> allPostList(int boardNum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		
		List<PostVo> list = dao.allPostList(sqlSession, boardNum);
		
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@Override
	public int getPostSeq() {
		SqlSession sqlSession = MybatisUtil.getSession();
		int seq = dao.getPostSeq(sqlSession);
		sqlSession.commit();
		sqlSession.close();
		return seq;
	}

	@Override
	public int insertAtf(AttachedfileVo atfVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.insertAtf(sqlSession, atfVo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int deletePost(PostVo postVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.deletePost(sqlSession, postVo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int updatePost(PostVo postVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.updatePost(sqlSession, postVo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public List<AttachedfileVo> getAttachedFile(int postnum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<AttachedfileVo> list = dao.getAttachedFile(sqlSession, postnum);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@Override
	public int deleteAtf(int atfnum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.deleteAtf(sqlSession, atfnum);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int deleteCmt(int cmtnum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.deleteCmt(sqlSession, cmtnum);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public AttachedfileVo selectAtf(int atfnum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		AttachedfileVo avo = dao.selectAtf(sqlSession, atfnum);
		sqlSession.close();
		return avo;
	}
	
}
