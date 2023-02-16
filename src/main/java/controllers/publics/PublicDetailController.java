package controllers.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CommentDao;
import daos.SongDao;
import models.Comment;
import models.Song;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;
	private CommentDao cmtDao;

	public PublicDetailController() {
		super();
		songDao = new SongDao();
		cmtDao = new CommentDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		int idSong = 0;
		try {
			idSong = Integer.parseInt(request.getParameter("did"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		Song itemSong = songDao.getItem(idSong);
		if (itemSong == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		// tăng lượt view
		HttpSession session = request.getSession();
		String hasVisited = (String) session.getAttribute("hasVisited: " + idSong);
		if (hasVisited == null) {
			session.setAttribute("hasVisited: " + idSong, "yes");
			session.setMaxInactiveInterval(10);
			// increse page view
			songDao.increaseView(idSong);
		}
		request.setAttribute("itemSong", itemSong);

		List<Song> relatedSong = songDao.getRelatedItems(itemSong, 2);
		request.setAttribute("relatedSong", relatedSong);
		List<Comment> listCmts = cmtDao.getItems(idSong);
		request.setAttribute("listCmts", listCmts);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
