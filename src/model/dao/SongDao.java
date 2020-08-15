package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Song;
import util.DBConnectionUtil;

public class SongDao {
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public static ArrayList<Song> getItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id ORDER BY id DESC";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItems;
	}

	public static Song getItem(int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id WHERE s.id=?";
		Song items = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				items = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return items;
	}

	public static int addItem(Song song) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO songs (name, preview_text, detail_text, picture, cat_id) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getItemCat().getIdCat());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public static int editItem(Song song) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET name= ?, preview_text= ?, detail_text= ?, picture = ?, cat_id= ? WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getItemCat().getIdCat());
			pst.setInt(6, song.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public static int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}
}
