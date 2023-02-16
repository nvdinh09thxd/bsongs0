package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Contact;
import util.DBConnectionUtil;

public class ContactDao extends AbstractDAO {

	public List<Contact> getItems() {
		con = DBConnectionUtil.getConnection();
		List<Contact> listItems = new ArrayList<>();
		String sql = "SELECT * FROM contacts";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact objItem = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, con);
		}
		return listItems;
	}

	public int delItem(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM contacts WHERE ID = ?";
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

	public int addItem(Contact item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contacts (name, email, website, message) VALUES (?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getWebsite());
			pst.setString(4, item.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, con);
		}
		return result;
	}
}
