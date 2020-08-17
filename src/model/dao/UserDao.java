package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;

public class UserDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<User> getItems() {
		conn = DBConnectionUtil.getConnection();
		ArrayList<User> ListItems = new ArrayList<>();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User objItem = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
				ListItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return ListItems;
	}

	public int addItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO users (username, password, fullname) VALUES (?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM users WHERE id = ?";
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

	public User getItem(int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		User item = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return item;
	}

	public int editItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE users SET username = ?, password = ?, fullname = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullName());
			pst.setInt(4, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public boolean hasUser(String username) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return false;
	}

	public User getItem(String username, String password) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username =? AND password = ?";
		User item = null;
		try {
			pst = conn.prepareStatement(sql);
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
			DBConnectionUtil.close(rs, pst, conn);
		}
		return item;
	}

	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return 0;
	}

}
