package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsDAO {
	Connection con;
	
	public AnalyticsDAO() {
		
	}
	
	public Map<String, Integer> getPurchasesForAllMonths(){
		con = DAOConnect.getConnection();
		
		 Map<String, Integer> stats = new HashMap<String, Integer>() {{
			    put("JANUARY", 0);
			    put("FEBRUARY", 0);
			    put("MARCH", 0);
			    put("APRIL", 0);
			    put("MAY", 0);
			    put("JUNE", 0);
			    put("JULY", 0);
			    put("AUGUST", 0);
			    put("SEPTEMBER", 0);
			    put("OCTOBER", 0);
			    put("NOVEMBER", 0);
			    put("DECEMBER", 0);
			}};
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select Created_at from po");
				
				while(rs.next()) {
					Date date = rs.getDate("created_at");
	
					LocalDate localDate = date.toLocalDate();
	
					Month month = localDate.getMonth();
					stats.put(month.toString(), stats.get(month.toString())+1);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(stats.get("DECEMBER"));
			return stats;
		
	}
	
	public List<String> getTopBooks(){
		Statement stmt;
		con = DAOConnect.getConnection();
		List<String>top_books = new ArrayList();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT bid FROM TOP_BOOKS ORDER BY TOTAL DESC LIMIT 10");
			while(rs.next()) {
				top_books.add(rs.getString("bid"));
				System.out.println(rs.getString("bid"));
			}
			con.close();
			rs.close();
		}catch (SQLException e) {
			
		}
		return top_books;
	}
	
	public void addNewBook(String bid) {
		String stmt = "INSERT INTO TOP_BOOKS(bid, total) VALUES (?, 1)";
		con = DAOConnect.getConnection();
		PreparedStatement p;
		try {
			p = con.prepareStatement(stmt);
			p.setString(1, bid);
			p.executeUpdate();
		
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void incrementABook(String title) {
		String stmt = "UPDATE TOP_BOOKS SET total = total + 1 WHERE bid = ?";
		con = DAOConnect.getConnection();
		PreparedStatement p;
		try {
			p = con.prepareStatement(stmt);
			p.setString(1, "%"+title+"%");
			p.executeUpdate();
		
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean bookExists(String title) {
		String stmt = "SELECT bid FROM TOP_BOOKS WHERE bid LIKE ?";
		con = DAOConnect.getConnection();
		Boolean status = false;
		PreparedStatement p;
		try {
			p = con.prepareStatement(stmt);
			p.setString(1, "%"+title+"%");
			ResultSet rs = p.executeQuery();
			
			status = rs.next();
		
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	
}
