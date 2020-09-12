package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Contact;
import util.DBConnectionUtil;

public class ContactDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Contact> getItems() {
		conn = DBConnectionUtil.getConnection();
		ArrayList<Contact> listItems = new ArrayList<>();
		String sql = "SELECT * FROM contacts";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact objItem = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItems;
	}

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM contacts WHERE ID = ?";
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

	public int addItem(Contact item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contacts (name, email, website, message) VALUES (?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getWebsite());
			pst.setString(4, item.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
}
