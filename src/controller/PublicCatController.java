package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Song;
import model.dao.SongDao;
import util.DefineUtil;
import model.dao.CatDao;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDao catDao;
	private SongDao songDao;

	public PublicCatController() {
		super();
		catDao = new CatDao();
		songDao = new SongDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCat = 0;
		int currentPage = 1;
		try {
			idCat = Integer.parseInt(request.getParameter("cid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		Category category = catDao.getItem(idCat);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
		int numberOfItems = songDao.numberOfItems(idCat);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		ArrayList<Song> listSongs = songDao.getItemsByCategoryPagination(offset, idCat);
		request.setAttribute("listSongs", listSongs);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
