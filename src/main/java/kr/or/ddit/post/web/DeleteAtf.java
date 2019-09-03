package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/deleteAtf")
public class DeleteAtf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPostService serv;
       
	@Override
	public void init() throws ServletException {
		serv = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		PostVo pvo = new PostVo();
		String atfNum = request.getParameter("atfNum");
		String boardNum = request.getParameter("boardNum");
		String postNum2 = request.getParameter("postNum2");
		String postNm = request.getParameter("postNm");
		String postCont = request.getParameter("postCont");
		String userId = request.getParameter("userId");
		
		pvo.setPostnm(postNm);
		pvo.setPostcont(postCont);
		pvo.setUserid(userId);
		
		int cnt = serv.deleteAtf(Integer.parseInt(atfNum));
		List<AttachedfileVo> atfList = serv.getAttachedFile(Integer.parseInt(postNum2));
		
		request.setAttribute("postNum2", postNum2);
		request.setAttribute("pvo", pvo);
		request.setAttribute("atfList", atfList);
		request.setAttribute("boardNum", boardNum);
		
		request.getRequestDispatcher("post/modifyPost.jsp").forward(request, response);
	}

}
