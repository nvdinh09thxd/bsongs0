package controllers.publics;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CommentDao;
import models.Comment;

public class PublicCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao cmtDao;

	public PublicCommentController() {
		super();
		cmtDao = new CommentDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int idSong = Integer.parseInt(request.getParameter("aid"));
		String username = request.getParameter("afullname");
		String cmt = request.getParameter("acmt");
		Comment item = new Comment(0, idSong, username, cmt, null, true);
		if (cmtDao.addItem(item) > 0) {
			Date date = new Date();
			String now = new SimpleDateFormat("dd/MM/yyyy").format(date);
			out.print("<b>" + item.getUsername() + ":</b>");
			out.print("<span>" + item.getComment() + " -----</span>");
			out.print("<i>" + now + "</i>");
			out.print("<br />");
		} else {
			out.print("<p style=\" color: red;\">Không thể lưu bình luận!</p>");
		}
	}
}