package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfo");
		if(!"admin".equals(userLogin.getUsername())) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
			rd.forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		String fullname = request.getParameter("fullname") ;
		UserDAO userDAO = new UserDAO();
		if(userDAO.ExistUsername(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
			rd.forward(request, response);
			return;
		}
		else {
		if(userDAO.AddItem(new User(0, username, password, fullname)) > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=1");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
			rd.forward(request, response);
		}
		}
	}
}
