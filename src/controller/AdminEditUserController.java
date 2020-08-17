package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.AuthUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public AdminEditUserController() {
		super();
		userDao = new UserDao();
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
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if ("admin".equals(userLogin.getUsername()) || (id == userLogin.getId())) {
			// chỉ có admin hoặc chính người dùng đó đăng nhập mới được phép sửa
			User itemUser = userDao.getItem(id);
			if (itemUser != null) {
				request.setAttribute("itemUser", itemUser);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
				return;
			}

		} else {
			// không có quyền
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=6");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if ("admin".equals(userLogin.getUsername()) || (id == userLogin.getId())) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			//VALIDATE DỮ LIỆU
			if ("".equals(username)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=1");
				rd.forward(request, response);
				return;
			}
			if ("".equals(password)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=2");
				rd.forward(request, response);
				return;
			}
			if ("".equals(fullname)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=3");
				rd.forward(request, response);
				return;
			}
			if (userDao.hasUser(username) && !userDao.getItem(id).getUsername().equals(username)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=4");
				rd.forward(request, response);
				return;
			}
			password = util.StringUtil.md5(password);
			User item = new User(id, username, password, fullname);
			if (userDao.editItem(item) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
				rd.forward(request, response);
			}
		} else {
			// không có quyền
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=6");
			return;
		}
	}

}
