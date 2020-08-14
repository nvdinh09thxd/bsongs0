package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDao;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		Category category = new Category(0, name);
		if (CatDao.addItem(category) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=1");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
			return;
		}
	}

}