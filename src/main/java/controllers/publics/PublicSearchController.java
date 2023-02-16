package controllers.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDao;
import models.Song;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;

	public PublicSearchController() {
		super();
		songDao = new SongDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		List<Song> listSongs = songDao.getItems(name);
		request.setAttribute("songName", name);
		request.setAttribute("numberOfPages", 1);
		request.setAttribute("currentPage", 1);
		for (Song itemSong : listSongs) {
			String songName = itemSong.getName();
			itemSong.setName(songName.replaceAll("(?i)" + name, "<b>" + name + "</b>"));
			//(?i) : thay thế không phân biệt hoa thường
		}
		request.setAttribute("listSongs", listSongs);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
