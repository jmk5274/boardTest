package kr.or.ddit.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private IUserService userServ;
	
    @Override
    public void init() throws ServletException {
    	userServ = UserServiceImpl.getInstance();
   	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("res", request.getAttribute("res"));
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		UserVo uvo = userServ.getUser(userId);
		
		String res = "";
		
		if(uvo==null) {
			res = "아이디를 다시 확인해주세요";
			request.setAttribute("res", res);
			doGet(request, response);
		}else if(uvo.getUserId().equals(userId) && uvo.getPass().equals(pass)) {
			HttpSession session = request.getSession();
			
			session.setAttribute("userVo", uvo);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else {
			res = "비밀번호를 다시 확인해주세요";
			request.setAttribute("res", res);
			doGet(request, response);
		}
		
	}

}
