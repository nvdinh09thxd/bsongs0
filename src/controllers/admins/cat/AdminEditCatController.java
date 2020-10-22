package controllers.admins.cat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CatDao;
import models.Category;
import util.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Lấy id danh mục truyền sang
		int catId = 0;
		try {
			catId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/views/admin/cats?msg=4");
			return;
		}
		CatDao catDao = new CatDao();
		// Lấy danh mục dựa vào id danh mục
		Category itemCat = catDao.getItem(catId);
		request.setAttribute("itemCat", itemCat);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setCharacterEncoding("utf-8");
		CatDao catDao = new CatDao();
		int catId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Category itemCat = new Category(catId, name);
		if (catDao.editItem(itemCat) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp?msg=0");
			rd.forward(request, response);
		}
	}
}
