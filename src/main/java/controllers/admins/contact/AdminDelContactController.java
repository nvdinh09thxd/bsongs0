package controllers.admins.contact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDao;
import util.AuthUtil;

public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/contacts?msg=4");
			return;
		}
		ContactDao contactDao = new ContactDao();
		if (contactDao.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/contacts?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/contacts?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}