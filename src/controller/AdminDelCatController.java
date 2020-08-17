package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CatDao;
import util.AuthUtil;

public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int catId = 0;
		try {
			catId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=4");
			return;
		}
		CatDao catDao = new CatDao();
		if (catDao.delItem(catId) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}