package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.loading.PrivateClassLoader;

import model.bean.Category;

import util.DBConnectionUntil;

public class CategoryDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<Category> getItems(){
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Category objItem = new Category(rs.getInt("id"), rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(st, conn, rs);
		}
		
		return listItem;
	}
	public int addItem(Category objItem) {
		int result = 0;
		conn = DBConnectionUntil.getConnection();
		String sql = "INSERT INTO categories(name) VALUES(?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objItem.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		return result;
	}
	public int delItem(int catId) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "DELETE FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		conn =DBConnectionUntil.getConnection();
		String sql2 = "DELETE FROM songs WHERE cat_id = ?";
		try {
			pst = conn.prepareStatement(sql2);
			pst.setInt(1, catId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return result;
	}
	public Category getItem(int catId) {
		Category item = null;
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs=pst.executeQuery();
			if(rs.next()) {
				 item = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return item;
	}
	public int editItem(Category objCat) {
		int result = 0;
		conn = DBConnectionUntil.getConnection();
		String sql = "UPDATE categories SET name = ? WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		return result;
	}
}
