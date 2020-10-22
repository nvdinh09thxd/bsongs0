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
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;

	public AdminEditSongController() {
		super();
		songDao = new SongDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int songId = 0;
		try {
			songId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=0");
			return;
		}
		Song itemSong = songDao.getItem(songId);
		request.setAttribute("itemSong", itemSong);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
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
		String description = request.getParameter("preview");
		String detail = request.getParameter("detail");
		int catId = Integer.parseInt(request.getParameter("catId"));
		int songId = Integer.parseInt(request.getParameter("id"));
		// get dữ liệu cũ
		Song song = songDao.getItem(songId);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=0");
			return;
		}
		// xu ly upload file
		String dirUpload = "files";
		String fileName = FileUtil.uploadFile(request, "picture", dirUpload);
		if (fileName == "") {
			fileName = song.getPicture();
		}
		// 1. tao doi tuong sau do luu cac thuoc tinh vao doi tuong
		// 2. viet phuong thuc insert table
		Song itemSong = new Song(songId, songName, description, detail, null, fileName, 0, new Category(catId, null));
		// insert database
		if (songDao.editItem(itemSong) > 0) {
			// Nếu có chọn file và đã thực hiện thành công thì xóa file cũ đi
			if (request.getPart("picture").getSubmittedFileName() != "") {
				String appPath = request.getServletContext().getRealPath("");
				String dirPath = appPath + dirUpload;
				String oldPathFileName = dirPath + File.separator + song.getPicture();
				File oldFile = new File(oldPathFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=2");
		} else {
			// Nếu có chọn file và không thành công thì xóa file đã upload lên
			if (request.getPart("picture").getSubmittedFileName() != "") {
				String appPath = request.getServletContext().getRealPath("");
				String oldPathFileName = appPath + dirUpload + File.separator + fileName;
				File oldFile = new File(oldPathFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp?msg=0");
			rd.forward(request, response);
		}
	}
}