package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import bean.BookBean;

public class BookDAO {
	Connection con;

	public BookDAO() {
		con = DAOConnect.getConnection();
	}
	
	public List<BookBean> getByCategory(String c) {
		con = DAOConnect.getConnection();
		List<BookBean> books = new ArrayList<BookBean>();

		try {
			String query = "select * from Book where category like ?";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, "%"+c+"%");
			
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				String bid= rs.getString("bid");
				String bookTitle = rs.getString("title");
				int price = rs.getInt("price");
//				String author = rs.getString("author");
				String category = rs.getString("category");
				System.out.println("category is" + category);
//				BookBean bean = new BookBean(bid, bookTitle, author, price, category);
				BookBean bean = new BookBean(bid, bookTitle, price, category);
				books.add(bean);
				System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t "  + category );
			}//end while loop
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return books;
		
	}
	
	
	public List<BookBean> getAllBooks(){
		con = DAOConnect.getConnection();
		List<BookBean> books = new ArrayList<BookBean>();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
			while(rs.next()) {
				String bid= rs.getString("bid");
				String bookTitle = rs.getString("title");
				int price = rs.getInt("price");
				String author = rs.getString("author");
				String category = rs.getString("category");
				System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t "  + category );
				BookBean bean = new BookBean(bid, bookTitle, author, price, category);
				books.add(bean);
			}//end while loop
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return books;
		
	}
	
	public List<BookBean> searchByTitle(String title){
		con = DAOConnect.getConnection();
		Statement stmt;
		List<BookBean> books = null;
		try {
			books = new ArrayList();

			String query = "select * from Book where title like ?";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, "%"+title+"%");
			
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
			String bid= rs.getString("bid");
			String bookTitle = rs.getString("title");
			int price = rs.getInt("price");
//			String author = rs.getString("author");
			String category = rs.getString("category");
//			BookBean bean = new BookBean(bid, bookTitle, author, price, category);
			BookBean bean = new BookBean(bid, bookTitle, price, category);
			books.add(bean);
//			System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t " + author + "\t " + category );
			System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t "  + category );
			
			}//end while loop
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return books;
		
	}
	
	public List<BookBean> searchByBID(String id){
		con = DAOConnect.getConnection();
		Statement stmt;
		List<BookBean> books = null;
		try {
			books = new ArrayList();

			String query = "select * from Book where bid like ?";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, "%"+id+"%");
			
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
			String bid= rs.getString("bid");
			String bookTitle = rs.getString("title");
			int price = rs.getInt("price");
//			String author = rs.getString("author");
			String category = rs.getString("category");
//			BookBean bean = new BookBean(bid, bookTitle, author, price, category);
			BookBean bean = new BookBean(bid, bookTitle, price, category);
			books.add(bean);
//			System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t " + author + "\t " + category );
			System.out.println("\t" + bid+ ",\t" + bookTitle+ "\t " + price + "\t "  + category );
			
			}//end while loop
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return books;
		
	}
}
