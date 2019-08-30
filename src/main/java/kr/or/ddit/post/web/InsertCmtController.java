package kr.or.ddit.post.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVo;

/**
 * Servlet implementation class InsertCmtController
 */
@WebServlet("/insertCmt")
public class InsertCmtController extends HttpServlet {
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
		
		HttpSession session = request.getSession();
		
		UserVo uvo = (UserVo) session.getAttribute("userVo");
		
		String postNum = request.getParameter("cmtPostNum");
		String cmtCont = request.getParameter("cmtCont");
		String userId = uvo.getUserId();
		
		CommentsVo cvo = new CommentsVo(0, cmtCont, null, Integer.parseInt(postNum), userId);
		
		serv.insertCmt(cvo);
		
		request.setAttribute("postNum", postNum);
		
		request.getRequestDispatcher("/selectPost").forward(request, response);;
	}

}
