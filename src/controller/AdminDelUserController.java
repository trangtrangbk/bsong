package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDelUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfo");
		User user = userDAO.getItemById(id);
		
		if("admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=0");
			return;
		}
		else {
			if("admin".equals(userLogin.getUsername())) {
				if(userDAO.DelItem(id) > 0) {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=3");
					return;
				}
				else {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=0");
					return;
				}
			}
			else {
				response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=0");
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
