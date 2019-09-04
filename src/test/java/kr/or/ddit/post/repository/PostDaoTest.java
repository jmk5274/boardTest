package kr.or.ddit.post.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.MybatisUtil;

public class PostDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(PostDaoTest.class);
	private IPostDao dao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		dao = PostDaoImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	//테스트에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	@Test
	public void getPostListTest() {
		/***Given***/
		int boardNum = 1;
		int page = 1;
		int pagesize = 5;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardNum", boardNum);
		map.put("page", page);
		map.put("pagesize", pagesize);
		
		/***When***/
		List<PostVo> list = dao.getPostList(sqlSession, map);
		
		/***Then***/
		assertEquals(5, list.size());
	}
	
	@Test
	public void selectPost() {
		/***Given***/
		int postNum = 11;

		/***When***/
		PostVo pvo = dao.selectPost(sqlSession, postNum);
		
		/***Then***/
		assertEquals("테스트", pvo.getPostnm());
	}
	
	@Test
	public void getCmtList() {
		/***Given***/
		int postNum = 101;

		/***When***/
		List<CommentsVo> list = dao.getCmtList(sqlSession, postNum);
		
		/***Then***/
		assertEquals(1, list.size());
	}
	
	@Test
	public void insertCmt() {
		/***Given***/
		CommentsVo cvo = new CommentsVo(0, "테스트2", null, 101, "cony");

		/***When***/
		int cnt = dao.insertCmt(sqlSession, cvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void getPostSeq() {
		/***Given***/

		/***When***/
		int seq = dao.getPostSeq(sqlSession);

		/***Then***/
		assertEquals(13, seq);
	}
	
	@Test
	public void insertPost() {
		/***Given***/
		PostVo pvo = new PostVo();
		pvo.setPostnm("테스트");	
		pvo.setPostcont("테스트입니다.아아아");
		pvo.setUserid("cony");
		pvo.setBoardnum(1);	
		pvo.setPostnum(5000);
		/***When***/
		int cnt = dao.insertPost(sqlSession, pvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void insertPost2() {
		/***Given***/
		PostVo pvo = new PostVo();
		pvo.setPostnm("테스트");	
		pvo.setPostcont("테스트입니다.아아아");
		pvo.setUserid("cony");
		pvo.setBoardnum(1);	
		pvo.setPostnum2(8);
		pvo.setGn(4);
		pvo.setPostnum(5001);
		
		/***When***/
		int cnt = dao.insertPost2(sqlSession, pvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void insertAtf() {
		/***Given***/
		AttachedfileVo avo = new AttachedfileVo();
		avo.setAtfnm("테스트");
		avo.setAtfpath("테스트용");
		avo.setPostnum(8);
		
		/***When***/
		int cnt = dao.insertAtf(sqlSession, avo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test 
	public void deletePost() {
		/***Given***/
		PostVo pvo = new PostVo();
		pvo.setDelstatus("Y");
		pvo.setPostnum(32);
		
		/***When***/
		int cnt = dao.deletePost(sqlSession, pvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void updatePost() {
		/***Given***/
		PostVo pvo = new PostVo();
		pvo.setPostnm("수정테스트");
		pvo.setPostcont("수정테스트입니다.");
		pvo.setPostnum(30);
		
		/***When***/
		int cnt = dao.updatePost(sqlSession, pvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void getAttachedFile() {
		/***Given***/
		int postnum = 22;

		/***When***/
		List<AttachedfileVo> list = dao.getAttachedFile(sqlSession, postnum);
		
		/***Then***/
		assertEquals(3, list.size());
	}
			
	@Test
	public void deleteAtf() {
		/***Given***/
		int atfnum = 18;

		/***When***/
		int cnt = dao.deleteAtf(sqlSession, atfnum);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void deleteCmt() {
		/***Given***/
		int cmtnum=19;

		/***When***/
		int cnt = dao.deleteCmt(sqlSession, cmtnum);
				
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void selectAtf() {
		/***Given***/
		int atfnum=12;

		/***When***/
		AttachedfileVo avo = dao.selectAtf(sqlSession, atfnum);
		
		/***Then***/
		assertEquals(12, avo.getAtfnum());
	}
}
