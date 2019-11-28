package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;


public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PublicContactController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/public/contact.jsp?id=2");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		
		ContactDAO contDAO = new ContactDAO();
		if(contDAO.addItem(new Contact(0, name, email, website, message)) > 0) {
			response.sendRedirect(request.getContextPath() + "/public/contact?msg=1");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/public/contact?msg=0");
		}
	}

}
