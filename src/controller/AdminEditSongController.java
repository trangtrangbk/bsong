package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.CategoryDAO;
import model.dao.SongDAO;
import util.AuthUtil;
import util.DefineUtil;
import util.FileUtil;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/auth/login");
			return;
		}
		SongDAO songDAO  = new SongDAO();
		CategoryDAO catDAO = new CategoryDAO();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
			return;
		}
		Song song = songDAO.getItem(id);
		if(song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
			return;
		}
		ArrayList<Category> listCat = catDAO.getItems();
		request.setAttribute("oldId", song.getCat_id());
		request.setAttribute("listCat", listCat);
		request.setAttribute("song", song);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SongDAO songDAO  = new SongDAO();
		
		int catId =0 ;
		int id = 0;
		try {
			catId = Integer.parseInt(request.getParameter("catId"));
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
			return;
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		String picture = "";
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
		Song song = songDAO.getItem(id);
		if(song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
			return;
		}
		if(fileName.isEmpty()) {
			picture = song.getPicture();
		}
		else{
			// doi ten file
			fileName = FileUtil.rename(fileName);
			String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String filePath = dirPath + File.separator + fileName;
			String oldFilePath = dirPath + File.separator + song.getPicture();
			File oldFile = new File(oldFilePath);
			if(oldFile.exists()) {
				oldFile.delete();
			}
		//	System.out.println(filePath);
			picture = fileName;
			filePart.write(filePath);
		}
		Song objSong = new Song(id, name, preview, detail, null, picture, 0, catId);
		if(songDAO.editItem(objSong) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=2");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/song/edit?msg=0");
			return;
		}
	}
}
