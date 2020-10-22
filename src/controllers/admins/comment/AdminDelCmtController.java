package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CommentDao;
import util.AuthUtil;

public class AdminDelCmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelCmtController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int cmtId = 0;
		try {
			cmtId = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/comments?msg=2");
			return;
		}
		CommentDao cmtDao = new CommentDao();
		if(cmtDao.delItem(cmtId)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/comments?msg=1");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/comments?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
