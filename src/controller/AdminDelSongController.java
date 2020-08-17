package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDao;
import util.AuthUtil;

public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDao songDao;

	public AdminDelSongController() {
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
		Song song = songDao.getItem(songId);
		String dirUpload = "files";
		if (songDao.delItem(songId) > 0) {
			//Nếu xóa bài hát thành công thì cũng xóa ảnh đi
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + dirUpload;
			String oldPathFileName = dirPath + File.separator + song.getPicture();
			File oldFile = new File(oldPathFileName);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=4");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
