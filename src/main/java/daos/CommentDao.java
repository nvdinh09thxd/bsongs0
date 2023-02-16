package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Comment;
import util.DBConnectionUtil;

public class CommentDao extends AbstractDAO {
	
	public int addItem(Comment item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO comments (id_song, username, comment) VALUES (?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getId_song());
			pst.setString(2, item.getUsername());
			pst.setString(3, item.getComment());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public List<Comment> getItems(int idSong) {
		con = DBConnectionUtil.getConnection();
		List<Comment> listItems = new ArrayList<>();
		String sql = "SELECT * FROM comments WHERE id_song = ? AND status > 0";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idSong);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment cmt = new Comment(rs.getInt("id"), rs.getInt("id_song"), rs.getString("username"),
						rs.getString("comment"), rs.getTimestamp("date_create"), rs.getBoolean("status"));
				listItems.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public List<Comment> getItems() {
		con = DBConnectionUtil.getConnection();
		List<Comment> listItems = new ArrayList<>();
		String sql = "SELECT * FROM comments";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment cmt = new Comment(rs.getInt("id"), rs.getInt("id_song"), rs.getString("username"),
						rs.getString("comment"), rs.getTimestamp("date_create"), rs.getBoolean("status"));
				listItems.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, con);
		}
		return listItems;
	}

	public int delItem(int cmtId) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM comments WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, cmtId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public int editItem(Comment item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE comments SET status = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, item.isStatus());
			pst.setInt(2, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}
}
