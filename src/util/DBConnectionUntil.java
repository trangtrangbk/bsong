package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUntil {
	private static Connection conn;
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/bsong?useUnicode=true&characterEncoding=UTF-8";
	private static final String PASSWORD="";
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USERNAME ,PASSWORD );
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement st) {
		if(st !=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement st, Connection conn, ResultSet rs) {
		close(rs);
		close(st);
		close(conn);
		
	}
	public static void close(PreparedStatement pst, Connection conn, ResultSet rs) {
		close(rs);
		close(pst);
		close(conn);
		
	}

	public static void close(PreparedStatement pst, Connection conn) {
		
		close(pst);
		close(conn);
		
	}
	public static void close(ResultSet rs) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pst) {
		if(pst !=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(DBConnectionUntil.getConnection());
	}
}
