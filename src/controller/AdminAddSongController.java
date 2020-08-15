package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.SongDao;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// get para trong form
		int idCat = Integer.parseInt(request.getParameter("category"));
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		// handle upload file
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
		String filePath = null;
		if (!"".equals(fileName)) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + "templates\\admin\\assets\\img\\";
			File saveDir = new File(dirPath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			filePath = dirPath + File.separator + fileName;
			filePart.write(filePath);
			// insert vào trong database song
			Song song = new Song(0, name, preview, detail, null, fileName, 0, new Category(idCat, null));
			if (SongDao.addItem(song) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/songs?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/song/add?msg=0");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/song/add?msg=2");
			return;
		}

	}
}