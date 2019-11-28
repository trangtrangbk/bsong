package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import util.DBConnectionUntil;
import util.DefineUtil;

public class ContactDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public int addItem(Contact cont) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "INSERT INTO contacts(name,email,website,message) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cont.getName());
			pst.setString(2, cont.getEmail());
			pst.setString(3, cont.getWebsite());
			pst.setString(4, cont.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		return result;
	}

	public ArrayList<Contact> getItems() {
		ArrayList<Contact> items = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql="SELECT id, name, email,website, message FROM contacts ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				Contact item = new Contact(id, name, email, website, message);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return items;
	}
	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUntil.getConnection();
		String sql="DELETE FROM contacts WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		
		return result;
	}
	public int getNumberOfItems() {
		int count=0;
		conn =DBConnectionUntil.getConnection();
		String sql = "SELECT COUNT(*) FROM contacts";
		try {
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return count;
	}

	public ArrayList<Contact> getItemsPagination(int offset) {
		ArrayList<Contact> listItems = new ArrayList<>();
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM contacts LIMIT ?,?";
		
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact objItem = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"),
						rs.getString("message"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return listItems;
	}
}