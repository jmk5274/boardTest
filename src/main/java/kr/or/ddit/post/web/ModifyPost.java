package kr.or.ddit.post.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifyPost")
public class ModifyPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNum = request.getParameter("boardNum");
		String postNum2 = request.getParameter("postNum2");
		String gn = request.getParameter("gn");
		String postNm = request.getParameter("postNm");
		String postCont = request.getParameter("postCont");
		String btnValue = request.getParameter("btnValue");
		
		if(btnValue.equals("답글")) {
			request.setAttribute("boardNum", boardNum);
			request.setAttribute("postNum2", postNum2);
			request.setAttribute("gn", gn);
			
			request.getRequestDispatcher("/post/writePost.jsp").forward(request, response);
		}else if(btnValue.equals("삭제")) {
			
		}else if(btnValue.equals("수정")) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
