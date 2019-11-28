package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import model.bean.Category;
import model.bean.Song;
import model.dao.CategoryDAO;
import model.dao.SongDAO;
import util.DefineUtil;

@WebServlet("/PublicIndexCatController")
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexCatController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("id"));
		int currentPage = 1;
		if(request.getParameter("page") != null)
		currentPage = Integer.parseInt(request.getParameter("page"));
		else {
			currentPage =1;
		}
		CategoryDAO catDAO = new CategoryDAO();
		Category category = catDAO.getItem(cid);
		SongDAO songDAO = new SongDAO();
		int numberOfItems = songDAO.numberOfItems(cid);
		int numberOfPages = (int) Math.ceil((float)numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if(currentPage > numberOfPages || currentPage <1) currentPage = 1;
		int offset = (currentPage -1)*DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Song> list = songDAO.getItemsPagination(offset,cid);
		System.out.println(list.size());
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("list", list);
		request.setAttribute("category",category );
		RequestDispatcher rd  = request.getRequestDispatcher("/public/cat.jsp?cid="+cid+"&page="+currentPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
