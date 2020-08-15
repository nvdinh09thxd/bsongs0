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
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Song item = SongDao.getItem(id);
		request.setAttribute("item", item);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// get para trong form
		int id = Integer.parseInt(request.getParameter("id"));
		int id_cat = Integer.parseInt(request.getParameter("category"));
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		// thực hiện xóa file ảnh cũ đi
		Song item = SongDao.getItem(id);
		String filePath1 = request.getServletContext().getRealPath("") + "templates\\admin\\assets\\img\\"
				+ item.getPicture();// lấy đường dẫn thực sự của file ảnh
		File file = new File(filePath1);
		file.delete();
		// handle upload file
		Part filePart = request.getPart("picture");
		String fileName = getName(filePart);
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
			Song song = new Song(id, name, preview, detail, null, fileName, 0, new Category(id_cat, null));
			if (SongDao.editItem(song) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/songs?msg=2");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/song/edit?msg=0&id="+id+"");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/song/edit?msg=2&id="+id+"");
			return;
		}

	}

	private String getName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}