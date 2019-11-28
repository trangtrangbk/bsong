package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Song;
import model.dao.CategoryDAO;
import model.dao.SongDAO;

public class PublicIndexDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicIndexDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SongDAO songDAO = new SongDAO();
		CategoryDAO catDAO = new CategoryDAO();
		songDAO.IncCounter(id);
		Song item = songDAO.getItem(id);
		int catId = item.getCat_id();
		ArrayList<Song> listRelative = songDAO.getListRel(id,catId);
		request.setAttribute("objSong", item);
		request.setAttribute("listRelative", listRelative);
		Category objCat = catDAO.getItem(catId);
		request.setAttribute("objCat", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
