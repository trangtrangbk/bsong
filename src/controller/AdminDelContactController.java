package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDAO;
import util.AuthUtil;


public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDelContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		ContactDAO conDAO = new ContactDAO();
		if(conDAO.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=3");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=0");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
