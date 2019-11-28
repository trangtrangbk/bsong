package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.CategoryDAO;
import model.dao.SongDAO;
import util.DefineUtil;


public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PublicIndexController() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		int numberOfSongs = songDAO.numberOfItems();
		int numberOfPages = (int)Math.ceil((float)numberOfSongs/DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage  = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if(currentPage > numberOfPages || currentPage < 1) currentPage = 1;
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Song> listSong = songDAO.getItemsPagination(offset);
		System.out.println(currentPage);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
		System.out.println(request.getServletContext().getRealPath(""));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String search = request.getParameter("search");
		System.out.println(search);
		SongDAO songDAO = new SongDAO();
		ArrayList<Song> listSearch  = songDAO.searchItems(search);
		System.out.println(listSearch.size());
		if(listSearch!= null) {
			request.setAttribute("listSearch", listSearch);
			RequestDispatcher rd = request.getRequestDispatcher("/public/searchResult.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/public/searchResult.jsp?msg=0");
		}
	}

}
