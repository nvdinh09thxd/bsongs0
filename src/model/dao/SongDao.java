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
import util.DefineUtil;

public class SongDao {
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public ArrayList<Song> getItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = c.id ORDER BY id DESC";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(0, rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItems;
	}

	public ArrayList<Song> getItems(int number) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = c.id ORDER BY id DESC LIMIT ?";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(0, rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}

	public ArrayList<Song> getItemsByCategory(int idCat) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = ? AND s.cat_id = c.id ORDER BY id DESC";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}

	public Song getItem(int idSong) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id WHERE s.id = ?";
		Song item = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idSong);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return item;
	}

	public int addItem(Song itemSong) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO songs (name, preview_text, detail_text, picture, cat_id) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemSong.getName());
			pst.setString(2, itemSong.getPreview_text());
			pst.setString(3, itemSong.getDetail_text());
			pst.setString(4, itemSong.getPicture());
			pst.setInt(5, itemSong.getItemCat().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int editItem(Song itemSong) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemSong.getName());
			pst.setString(2, itemSong.getPreview_text());
			pst.setString(3, itemSong.getDetail_text());
			pst.setString(4, itemSong.getPicture());
			pst.setInt(5, itemSong.getItemCat().getId());
			pst.setInt(6, itemSong.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public ArrayList<Song> getRelatedItems(Song itemSong, int number) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = ? AND s.cat_id = c.id AND s.id != ? ORDER BY id DESC LIMIT ?";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemSong.getItemCat().getId());
			pst.setInt(2, itemSong.getId());
			pst.setInt(3, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}

	public void increaseView(int idSong) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET  counter = counter + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idSong);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
	}

	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return 0;
	}

	public int numberOfItems(int idCat) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<Song> getItemPagination(int offset) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id ORDER BY id DESC LIMIT ?, ?";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}

	public int delItem(int songId) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, songId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public ArrayList<Song> getItemsByCategoryPagination(int offset, int idCat) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id WHERE cat_id = ? ORDER BY id DESC LIMIT ?, ?";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}

	public ArrayList<Song> getItems(String name) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE (s.name LIKE ? OR s.name LIKE ? OR s.name LIKE ?) AND s.cat_id = c.id ORDER BY id DESC";
		ArrayList<Song> listItems = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			String a = "% " + name + " %";
			String b = name + " %";
			String c = "% " + name;
			pst.setString(1, a);
			pst.setString(2, b);
			pst.setString(3, c);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song ObjItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("cname")));
				listItems.add(ObjItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	
	public static void main(String[] args) {
		SongDao songDao = new SongDao();
		ArrayList<Song> recentSongs = songDao.getItems(6);
		System.out.println(recentSongs.size());
	}
	
}
