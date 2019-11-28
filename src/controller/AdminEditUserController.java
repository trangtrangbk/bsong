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



public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminEditUserController() {
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
		UserDAO userDAO = new UserDAO();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=0");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfo");
		User user = userDAO.getItemById(id);
		
		if("admin".equals(userLogin.getUsername()) || id == userLogin.getId()) {
			if(user!=null) {
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
				rd.forward(request, response);
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
			rd.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserDAO userDAO = new UserDAO();

		String oldName = request.getParameter("oldName");
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfo");
		User user = userDAO.getItemById(id);
		
		if("admin".equals(userLogin.getUsername()) || id == userLogin.getId()) {
			String username = request.getParameter("username");  
			String password = StringUtil.md5(request.getParameter("password"));
			String fullname = request.getParameter("fullname");
			if(!username.equals(oldName)) {
				if(userDAO.ExistUsername(username)) {
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
					rd.forward(request, response);
					return;
				}
			}
			if(userDAO.EditItem(new User(id, username, password, fullname)) > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=2");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
				rd.forward(request, response);
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index?msg=0");
			rd.forward(request, response);
		}
		
	}

}
