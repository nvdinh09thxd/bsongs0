package controllers.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;
import util.AuthUtil;
import util.StringUtil;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public LoginController() {
		super();
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nếu đã đăng nhập rồi thì chuyển hướng sang trang admin luôn
		if (AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return; // thoát luôn, không xử lý tiếp theo
		}
		// Chuyển tiếp sang trang login
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get dữ liệu từ form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// mã hóa password bằng phương thức md5
		password = StringUtil.md5(password);
		// Nếu tồn tại username và password trong csdl thì chuyển hướng đến trang admin
		if (userDao.getItem(username, password) != null) {
			HttpSession session = request.getSession();
			User userLogin = userDao.getItem(username, password);
			session.setAttribute("userLogin", userLogin);
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			// Nếu không tồn tại username và password trong csdl thì chuyển tiếp đến trang
			// login và thông báo lỗi
			RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp?err=0");
			rd.forward(request, response);
		}
	}
}
