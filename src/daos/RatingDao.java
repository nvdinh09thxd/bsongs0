package daos;

import java.sql.SQLException;

import models.Rating;
import util.DBConnectionUtil;

public class RatingDao extends AbstractDAO {

	public int addItem(Rating objItem) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO rating (id_song, rating) VALUES (?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, objItem.getId_song());
			pst.setFloat(2, objItem.getRating());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public int editItem(Rating objItem) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE rating SET rating = (? + rating*count)/(count+1), count = count + 1 WHERE id_song = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setFloat(1, objItem.getRating());
			pst.setInt(2, objItem.getId_song());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public boolean hasRaty(int idSong) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM rating WHERE id_song = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idSong);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return false;
	}

	public float getRating(int idSong) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT rating FROM rating WHERE id_song = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idSong);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getFloat("rating");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return 0;
	}

	public static void main(String[] args) {
		RatingDao a = new RatingDao();
		System.out.println(a.getRating(12));
	}
}
