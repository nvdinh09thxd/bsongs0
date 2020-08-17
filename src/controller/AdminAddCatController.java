package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDao;
import util.AuthUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// chuyển tiếp sang trang add cat
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
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
		String name = request.getParameter("name");
		Category itemCat = new Category(0, name);
		// Nếu thêm thành công thì chuyển hướng sang trang index cat
		if (catDao.addItem(itemCat) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=1");
			return;
		} else {
			// Nếu không thành công thì chuyển tiếp sang trang add cat
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=0");
			rd.forward(request, response);
		}
	}
}