package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDao;
import util.AuthUtil;

public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao cmtDao;

	public AdminIndexCommentController() {
		super();
		cmtDao = new CommentDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		CommentDao cmtDao = new CommentDao();
		ArrayList<Comment> listCmts = cmtDao.getItems();
		request.setAttribute("listCmts", listCmts);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("aid"));
		String src = request.getParameter("asrc");
		int idx = src.lastIndexOf("/");
		String firstName = src.substring(0, idx + 1);
		String lastName = src.substring(idx + 1);
		String fileName = "";
		boolean active = true;
		if (lastName.equals("active.gif")) {
			fileName = firstName + "deactive.gif";
			active = false;
		} else {
			fileName = firstName + "active.gif";
		}
		Comment cmt = new Comment(id, 0, "", "", null, active);
		if (cmtDao.editItem(cmt) > 0) {
			out.print(fileName);
		} else {
			out.print(src);
		}
	}
}