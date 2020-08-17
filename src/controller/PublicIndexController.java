package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDao;
import util.DefineUtil;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;

	public PublicIndexController() {
		super();
		songDao = new SongDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numberOfItems = songDao.numberOfItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Song> listSongs = songDao.getItemPagination(offset);
		request.setAttribute("listSongs", listSongs);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
