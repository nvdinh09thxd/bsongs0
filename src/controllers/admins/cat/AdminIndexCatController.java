package controllers.admins.cat;

import java.io.IOException;
import java.util.ArrayList;
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
	private CatDao catDao;
	private List<Category> listCat;

	public AdminIndexCatController() {
		super();
		catDao = new CatDao();
		listCat = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		 Nếu chưa đăng nhập thì chuyển hướng sang trang login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
//		 Lấy danh sách danh mục và chuyển tiếp sang trang index cat để hiển thị ra
		if (listCat.size() > 0) {
			if (request.getParameter("cat_id_new") != null) {
				int catIdNew = Integer.parseInt(request.getParameter("cat_id_new"));
				Category itemCatNew = catDao.getItem(catIdNew);
				listCat.add(itemCatNew);
			} else if (request.getParameter("cat_id_edit") != null) {
				int catIdEdit = Integer.parseInt(request.getParameter("cat_id_edit"));
				Category itemCatEdit = null;
				for (Category el : listCat) {
					if (el.getId() == catIdEdit)
						itemCatEdit = el;
				}
				Category itemCatEdited = catDao.getItem(catIdEdit);
				int index = listCat.indexOf(itemCatEdit);
				listCat.set(index, itemCatEdited);
			} else if (request.getParameter("cat_id_del") != null) {
				int catIdDel = Integer.parseInt(request.getParameter("cat_id_del"));
				listCat.removeIf(el -> el.getId() == catIdDel);
			}
		} else {
			listCat = catDao.getItems();
		}
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
