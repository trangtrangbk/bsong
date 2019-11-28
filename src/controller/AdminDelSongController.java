package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SongDAO;

public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDelSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(songDAO.delItems(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=3");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
