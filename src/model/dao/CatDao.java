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
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public static ArrayList<Category> getItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories";
		ArrayList<Category> listCat = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category ObjCat = new Category(rs.getInt("id"), rs.getString("name"));
				listCat.add(ObjCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}

		return listCat;
	}

	public static Category getItem(int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE id=" + id;
		Category itemCat = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				itemCat = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return itemCat;
	}

	public static int addItem(Category category) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO categories(name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public static int editItem(Category category) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE categories SET name= ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category.getName());
			pst.setInt(2, category.getIdCat());
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
		String sql = "DELETE FROM categories WHERE id = ?";
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

	public static void main(String[] args) {
		Category itemCat = CatDao.getItem(10);
		if (itemCat != null) {
			System.out.println(itemCat.getName());
		} else {
			System.out.println("Trong!");
		}
	}
}
