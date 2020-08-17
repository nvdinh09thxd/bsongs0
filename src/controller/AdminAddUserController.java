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

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public AdminAddUserController() {
		super();
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		//chỉ admin mới được thêm người dùng
		if(!"admin".equals(userLogin.getUsername())) {
			//không được phép
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=5");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		//chỉ admin mới được thêm người dùng
		if(!"admin".equals(userLogin.getUsername())) {
			//không được phép
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=5");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		//VALIDATE DỮ LIỆU
		if("".equals(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=1");
			rd.forward(request, response);
			return;
		}
		if("".equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=2");
			rd.forward(request, response);
			return;
		}
		if("".equals(fullname)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=3");
			rd.forward(request, response);
			return;
		}
		if(userDao.hasUser(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=4");
			rd.forward(request, response);
			return;
		}
		password = util.StringUtil.md5(password);
		User item = new User(0, username, password, fullname);
		if(userDao.addItem(item)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/users?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}
}
