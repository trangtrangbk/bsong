package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.User;
import model.dao.CategoryDAO;
import model.dao.UserDAO;
import util.StringUtil;


public class AdminAuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAuthLoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User user = userDAO.checkLogin(username,password);
		if(user !=null) {
			session.setAttribute("userInfo", user);
			response.sendRedirect(request.getContextPath() +"/admin/index");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath() +"/auth/login?msg=Err");
		}
	}

}
/*Hien thi giao dien public 4 trang
 * Them sua xoa danh muc*/
