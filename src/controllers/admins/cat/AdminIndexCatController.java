package controllers.admins.cat;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CatDao;
import models.Category;
import util.AuthUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nếu chưa đăng nhập thì chuyển hướng sang trang login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Lấy danh sách danh mục và chuyển tiếp sang trang index cat để hiển thị ra
		CatDao catDao = new CatDao();
		List<Category> listCat = catDao.getItems();
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
