package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.GlobalConstant;
import models.Category;
import models.Song;
import util.DBConnectionUtil;

public class SongDao extends AbstractDAO {

	public List<Song> getItems() {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = c.id ORDER BY id DESC";
		List<Song> listItems = new ArrayList<>();
		try {
			st = con.createStatement();
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
			DBConnectionUtil.close(rs, st, con);
		}
		return listItems;
	}

	public List<Song> getItems(int number) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = c.id ORDER BY id DESC LIMIT ?";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public List<Song> getItemsByCategory(int idCat) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = ? AND s.cat_id = c.id ORDER BY id DESC";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public Song getItem(int idSong) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id WHERE s.id = ?";
		Song item = null;
		try {
			pst = con.prepareStatement(sql);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return item;
	}

	public int addItem(Song itemSong) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO songs (name, preview_text, detail_text, picture, cat_id) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, itemSong.getName());
			pst.setString(2, itemSong.getDescription());
			pst.setString(3, itemSong.getDetail());
			pst.setString(4, itemSong.getPicture());
			pst.setInt(5, itemSong.getCat().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public int editItem(Song itemSong) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, itemSong.getName());
			pst.setString(2, itemSong.getDescription());
			pst.setString(3, itemSong.getDetail());
			pst.setString(4, itemSong.getPicture());
			pst.setInt(5, itemSong.getCat().getId());
			pst.setInt(6, itemSong.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public List<Song> getRelatedItems(Song itemSong, int number) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE s.cat_id = ? AND s.cat_id = c.id AND s.id != ? ORDER BY id DESC LIMIT ?";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, itemSong.getCat().getId());
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public void increaseView(int idSong) {
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET  counter = counter + 1, data_create = data_create WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idSong);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
	}

	public int numberOfItems() {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, con);
		}
		return 0;
	}

	public int numberOfItems(int idCat) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return 0;
	}

	public List<Song> getItemPagination(int offset) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id ORDER BY id DESC LIMIT ?, ?";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, GlobalConstant.NUMBER_PER_PAGE);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public int delItem(int songId) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sqlSong = "DELETE FROM songs WHERE id = ?";
		String sqlCmt = "DELETE FROM comments WHERE id_song = ?";
		String sqlRating = "DELETE FROM rating WHERE id_song = ?";
		try {
			pst = con.prepareStatement(sqlSong);
			pst.setInt(1, songId);
			pst.executeUpdate();
			pst = con.prepareStatement(sqlCmt);
			pst.setInt(1, songId);
			pst.executeUpdate();
			pst = con.prepareStatement(sqlRating);
			pst.setInt(1, songId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public List<Song> getItemsByCategoryPagination(int offset, int idCat) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs s JOIN categories c ON s.cat_id = c.id WHERE cat_id = ? ORDER BY id DESC LIMIT ?, ?";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.setInt(2, offset);
			pst.setInt(3, GlobalConstant.NUMBER_PER_PAGE);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public List<Song> getItems(String name) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT s.*, c.name AS cname FROM songs AS s JOIN categories AS c WHERE (s.name LIKE ? OR s.name LIKE ? OR s.name LIKE ?) AND s.cat_id = c.id ORDER BY id DESC";
		List<Song> listItems = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
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
			DBConnectionUtil.close(rs, pst, con);
		}
		return listItems;
	}

	public static void main(String[] args) {
		SongDao songDao = new SongDao();
		List<Song> recentSongs = songDao.getItems(6);
		System.out.println(recentSongs.size());
	}

}
