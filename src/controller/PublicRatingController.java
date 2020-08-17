package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Rating;
import model.dao.RatingDao;

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
		Rating item = new Rating(idSong, score, 0, null);
		if (raty.hasRaty(idSong)) {
			if (raty.editItem(item) > 0) {
				response.getWriter().println("Cám ơn bạn đã đánh giá!");
			} else {
				response.getWriter().println("Không thể lưu!");
			}
		} else {
			if (raty.addItem(item) > 0) {
				response.getWriter().println("Cám ơn bạn đã đánh giá!");
			} else {
				response.getWriter().println("Không thể lưu!");
			}
		}
	}
}
