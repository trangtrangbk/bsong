package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Song;
import util.DBConnectionUntil;
import util.DefineUtil;
public class SongDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public ArrayList<Song> getItems() {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(st, conn, rs);
		}
		
		return listItem;
	}
	public ArrayList<Song> getItemsById(int id) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs WHERE cat_id =? ORDER BY id ASC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return listItem;
	}
	public void IncCounter(int id) {
		conn = DBConnectionUntil.getConnection();
		String sql = "UPDATE songs SET counter= counter+1 WHERE id =?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
			if(result > 0) {
				System.out.println("DONE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
	}
	public Song getItem(int id) {
		Song item = null;
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				 item = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
							rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
							rs.getInt("counter"), rs.getInt("cat_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return item;
	}
	public ArrayList<Song> getListRel(int id,int catId) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs WHERE id!=? AND cat_id=? LIMIT 3";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, catId);
			rs= pst.executeQuery();
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(st, conn, rs);
		}
		
		return listItem;
	}
	public ArrayList<Song> getListNew() {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs ORDER BY id DESC LIMIT 5";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(st, conn, rs);
		}
		
		return listItem;
	}
	public int addItem(Song objSong) {
		int result = 0;
		conn = DBConnectionUntil.getConnection();
		String sql = "INSERT INTO songs(name,preview_text,detail_text,picture,cat_id) VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,objSong.getName());
			pst.setString(2, objSong.getPre());
			pst.setString(3, objSong.getDetail());
			pst.setString(4, objSong.getPicture());
			pst.setInt(5, objSong.getCat_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		return result;
	}
	public int editItem(Song song) {
		int result = 0;
		conn =DBConnectionUntil.getConnection();
		String sql = "UPDATE  songs SET name=?,preview_text=?,detail_text=?,picture=?,cat_id=?"
				+ " WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,song.getName());
			pst.setString(2, song.getPre());
			pst.setString(3, song.getDetail());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCat_id());
			pst.setInt(6,song.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn);
		}
		return result;
	}
	public ArrayList<Song> searchItems(String search) {
		ArrayList<Song> listItems = new ArrayList<>();
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT * FROM songs WHERE name LIKE ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + search + "%");
			rs = pst.executeQuery();
			CategoryDAO catDAO = new CategoryDAO();
			while(rs.next()) {				
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItems.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		return listItems;
	}
	public int delItems(int id) {
		int result =0;
		conn = DBConnectionUntil.getConnection();
		String sql = "DELETE from songs where id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return result;
	}
	public int numberOfItems() {
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT COUNT(*) FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(pst, conn, rs);
		}
		
		return 0;
	}
	public ArrayList<Song> getItemsPagination(int offset) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUntil.getConnection();
		String sql= "SELECT * FROM songs ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUntil.close(st, conn, rs);
		}
		
		return listItem;
	}
	public int numberOfItems(int cid) {
		int count=0;
		conn =DBConnectionUntil.getConnection();
		String sql = "SELECT COUNT(*) FROM songs WHERE cat_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
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
	public ArrayList<Song> getItemsPagination(int offset, int cid) {
		ArrayList<Song> listItems = new ArrayList<>();
		conn  = DBConnectionUntil.getConnection();
		String sql = "SELECT s.id,s.name,preview_text, detail_text, date_create,picture,"
				+ "counter,cat_id,c.name FROM songs AS s INNER  JOIN categories AS c ON c.id = s.cat_id"
				+ " WHERE cat_id=? LIMIT ?,?";
		
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"), rs.getString("detail_text"),
						rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("counter"), rs.getInt("cat_id"));
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