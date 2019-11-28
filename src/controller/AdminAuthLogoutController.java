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


public class AdminAuthLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAuthLogoutController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath() +"/admin/index");
		}
	}

}
/*Hien thi giao dien public 4 trang
 * Them sua xoa danh muc*/
