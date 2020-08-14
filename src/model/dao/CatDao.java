package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class CatDao {
	private static Connection conn;
	private static Statement st;
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
	public static void main(String[] args) {
		Category itemCat = CatDao.getItem(10);
		if(itemCat!=null) {
		System.out.println(itemCat.getName());
		} else {
			System.out.println("Trong!");
		}
	}
}
