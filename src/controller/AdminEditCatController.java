package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.AuthUtil;


public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		int catId = Integer.parseInt(request.getParameter("id"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = catDAO.getItem(catId);
		request.setAttribute("objCat", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int catId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat=new Category(catId, name);
		if(catDAO.editItem(objCat) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=2");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=0");
			return;
		}
	}
}
/*Hien thi giao dien public 4 trang
 * Them sua xoa danh muc*/
