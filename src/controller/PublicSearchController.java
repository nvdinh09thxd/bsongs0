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

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;

	public PublicSearchController() {
		super();
		songDao = new SongDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		ArrayList<Song> listSongs = songDao.getItems(name);
		request.setAttribute("songName", name);
		request.setAttribute("numberOfPages", 1);
		request.setAttribute("currentPage", 1);
		for (Song itemSong : listSongs) {
			String songName = itemSong.getName();
			itemSong.setName(songName.replaceAll("(?i)" + name, "<b>" + name + "</b>"));
			//(?i) : thay thế không phân biệt hoa thường
		}
		request.setAttribute("listSongs", listSongs);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
