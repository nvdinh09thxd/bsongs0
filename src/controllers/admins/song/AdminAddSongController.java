package controllers.admins.song;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDao;
import models.Category;
import models.Song;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		SongDao songDao = new SongDao();
		// lay cac gia tri khong phai la file
		String songName = request.getParameter("name");
		if ("".equals(songName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?msg=1");
			rd.forward(request, response);
			return;
		}
		int catId = Integer.parseInt(request.getParameter("catId"));
		if (catId == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?msg=4");
			rd.forward(request, response);
			return;
		}
		String description = request.getParameter("preview");
		if ("".equals(description)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?msg=2");
			rd.forward(request, response);
			return;
		}
		String detail = request.getParameter("detail");
		if ("".equals(detail)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?msg=3");
			rd.forward(request, response);
			return;
		}
		// xu ly upload file
		String dirUpload = "files";
		String fileName = FileUtil.uploadFile(request, "picture", dirUpload);
		// 1. tao doi tuong sau do luu cac thuoc tinh vao doi tuong
		// 2. viet phuong thuc insert table
		Song itemSong = new Song(0, songName, description, detail, null, fileName, 0, new Category(catId, null));
		// insert database
		if (songDao.addItem(itemSong) > 0) {
			// Nếu insert thành công thì chuyển hướng sang trang index và thông báo cho
			// người dùng biết
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=1");
		} else {
			// Nếu không thành công thì xóa ảnh vừa upload đi
			String appPath = request.getServletContext().getRealPath("");
			String oldPathFileName = appPath + dirUpload + File.separator + fileName;
			File oldFile = new File(oldPathFileName);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?msg=0");
			rd.forward(request, response);
		}
	}
}