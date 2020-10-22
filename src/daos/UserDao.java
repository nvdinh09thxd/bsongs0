package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import util.DBConnectionUtil;

public class UserDao extends AbstractDAO {

	public List<User> getItems() {
		con = DBConnectionUtil.getConnection();
		List<User> listItems = new ArrayList<>();
		String sql = "SELECT * FROM users ORDER BY id";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User objItem = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, con);
		}
		return listItems;
	}

	public int addItem(User item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO users (username, password, fullname) VALUES (?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM users WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public User getItem(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		User item = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return item;
	}

	public int editItem(User item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE users SET username = ?, password = ?, fullname = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setInt(4, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}

	public boolean hasUser(String username) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
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

	public User getItem(String username, String password) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username =? AND password = ?";
		User item = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, con);
		}
		return item;
	}

	public int numberOfItems() {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, con);
		}
		return 0;
	}

}
