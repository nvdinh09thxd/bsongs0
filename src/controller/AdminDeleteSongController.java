package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDao;

public class AdminDeleteSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Song objSong = SongDao.getItem(id);
		
		String filePath = request.getServletContext().getRealPath("")+"templates\\admin\\assets\\img\\"+objSong.getPicture();
		File file = new File(filePath);
		file.delete();
		if (SongDao.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}