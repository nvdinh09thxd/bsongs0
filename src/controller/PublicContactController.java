package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDao;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;

	public PublicContactController() {
		super();
		contactDao = new ContactDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// get data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");

		// validate du lieu
		if ("".equals(name)) {
			request.getRequestDispatcher("/public/contact.jsp?msg=2").forward(request, response);
			return;
		}
		if ("".equals(email)) {
			request.getRequestDispatcher("/public/contact.jsp?msg=3").forward(request, response);
			return;
		}
		if ("".equals(website)) {
			request.getRequestDispatcher("/public/contact.jsp?msg=4").forward(request, response);
			return;
		}
		if ("".equals(message)) {
			request.getRequestDispatcher("/public/contact.jsp?msg=5").forward(request, response);
			return;
		}

		Contact item = new Contact(0, name, email, website, message);

		if (contactDao.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/contact?msg=1");
			return;
		} else {
			request.getRequestDispatcher("/public/contact.jsp?msg=0").forward(request, response);
			return;
		}
	}

}
