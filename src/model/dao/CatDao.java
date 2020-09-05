package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class CatDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Category> getItems() {
		conn = DBConnectionUtil.getConnection();
		ArrayList<Category> ListItems = new ArrayList<>();
		String sql = "SELECT * FROM categories ORDER BY id";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category item = new Category(rs.getInt("id"), rs.getString("name"));
				ListItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return ListItems;
	}

	public int addItem(Category item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO categories (name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int delItem(int catId) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM categories WHERE ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public Category getItem(int catId) {
		conn = DBConnectionUtil.getConnection();
		Category item = null;
		String sql = "SELECT * FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return item;
	}

	public int editItem(Category item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM categories";
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
