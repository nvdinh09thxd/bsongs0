package controllers.publics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.RatingDao;
import models.Rating;

public class PublicRatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RatingDao raty;

	public PublicRatingController() {
		super();
		raty = new RatingDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int idSong = Integer.parseInt(request.getParameter("aid"));
		float score = (float) 0.0;
		if (request.getParameter("ascore") != null) {
			score = Float.parseFloat(request.getParameter("ascore"));
		}
		Rating item = new Rating(0, idSong, score, 0, null);
		if (raty.hasRaty(idSong)) {// Nếu đã đánh giá trước đó thì cập nhật lại
			if (raty.editItem(item) > 0) {
				response.getWriter().println("Cám ơn bạn đã đánh giá!");
			} else {
				response.getWriter().println("Không thể lưu đánh giá!");
			}
		} else {// Nếu chưa đánh giá thì thêm mới
			if (raty.addItem(item) > 0) {
				response.getWriter().println("Cám ơn bạn đã đánh giá!");
			} else {
				response.getWriter().println("Không thể lưu đánh giá!");
			}
		}
	}
}
