package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
			return;
		}
		UserDao userDao = new UserDao();
		User user = userDao.getItem(id);
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if("admin".equals(user.getUsername())) {
			//không được phép xóa admin
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=7");
			return;
		} else {
			if("admin".equals(userLogin.getUsername())) {
				//được phép xóa
				if(userDao.delItem(id)>0) {
					response.sendRedirect(request.getContextPath() + "/admin/users?msg=3");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/users?msg=0");
					return;
				}
				
			} else {
				//không được phép
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=7");
				return;
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
