package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CatDao;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (CatDao.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}