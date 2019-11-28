package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import model.bean.User;
import util.DBConnectionUntil;
import util.DefineUtil;
public class UserDAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	public ArrayList<User> getItems() {
		ArrayList<User> listItems = new ArrayList<>();
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				User objItem = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("fullname"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return listItems;
	}
	public int AddItem(User user) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return result;
	}

	public int EditItem(User user) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "UPDATE users SET username =?,password=?,fullname=? WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setInt(4, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return result;
	}
	public int DelItem(int id) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "DELETE FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return result;
	}
	public User getItemById(int id) {
		User user = null;
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM users WHERE id =?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("fullname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return user;
	}
	public User checkLogin(String name,String pass) {
		User user = null;
		conn = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return user;
	}
	public int getNumberOfItems() {
		int count=0;
		conn =DBConnectionUntil.getConnection();
		String sql = "SELECT COUNT(*) FROM users";
		try {
			st = conn.createStatement();
			rs= st.executeQuery(sql);
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

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> listItems = new ArrayList<>();
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM users LIMIT ?,?";
		
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User objItem = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return listItems;
	}
	public boolean ExistUsername(String username) {
		conn = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM users WHERE username =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return false;
	}
	
}
