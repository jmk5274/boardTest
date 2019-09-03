package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;

public interface IPostDao {
	
	public List<PostVo> allPostList(SqlSession sqlSession, int boardNum);
	
	/**
	* Method : getPostList
	* 작성자 : PC-13
	* 변경이력 :
	* @param sqlSession
	* @param boardNum
	* @return
	* Method 설명 : boardNum을 파라미터로 보내 일치하는 게시판 리스트를 가져온다.
	 */
	public List<PostVo> getPostList(SqlSession sqlSession, Map map);
	
	/**
	* Method : selectPost
	* 작성자 : PC-13
	* 변경이력 :
	* @param sqlSession
	* @param postNum
	* @return
	* Method 설명 : postNum을 파라미터로 보내 일치하는 게시글 정보를 가져온다.
	 */
	public PostVo selectPost(SqlSession sqlSession, int postNum);
	
	/**
	* Method : getCmtList
	* 작성자 : PC-13
	* 변경이력 :
	* @param sqlSession
	* @param postNum
	* @return
	* Method 설명 : postNum을 파라미터로 보내 일치하는 덧글 리스트를 가져온다.
	 */
	public List<CommentsVo> getCmtList(SqlSession sqlSession, int postNum);
	
	/**
	* Method : insertCmt
	* 작성자 : PC-13
	* 변경이력 :
	* @param sqlSession
	* @param cvo
	* @return
	* Method 설명 : 덧글 저장
	 */
	public int insertCmt(SqlSession sqlSession, CommentsVo cvo);
	
	public int getPostSeq(SqlSession sqlSession);
	
	public int insertPost(SqlSession sqlSession, PostVo postVo);
	
	public int insertPost2(SqlSession sqlSession, PostVo postVo);
	
	public int insertAtf(SqlSession sqlSession, AttachedfileVo atfVo);
	
	public int deletePost(SqlSession sqlSession, PostVo postVo);
	
	public int updatePost(SqlSession sqlSession, PostVo postVo);
	
	public List<AttachedfileVo> getAttachedFile(SqlSession sqlSession, int postnum);
	
	public int deleteAtf(SqlSession sqlSession, int atfnum);
	
	public int deleteCmt(SqlSession sqlSession, int cmtnum);
	
	public AttachedfileVo selectAtf(SqlSession sqlSession, int atfnum);

}
