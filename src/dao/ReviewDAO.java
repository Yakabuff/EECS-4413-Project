package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReviewBean;

public class ReviewDAO {
	
	Connection con;
	
	public ReviewDAO() {
		
	}
	
	public void addReview(int rating, String name, String bid, String message) {
		con = DAOConnect.getConnection();
		String query = "INSERT INTO Rating(rating,name,bid, message) VALUES (?,?,?,?)";
		try {
			PreparedStatement p = con.prepareStatement(query);
			p.setInt(1, rating);
			p.setString(2, name);
			p.setString(3, bid);
			p.setString(4, message);
			
			System.out.println(p.executeUpdate());
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public List<ReviewBean> getReviews(String bid){
		con = DAOConnect.getConnection();
		List<ReviewBean> reviews = new ArrayList();
		String query = "select * from Rating where bid like ?";
		PreparedStatement p;
		try {
			p = con.prepareStatement(query);
			p.setString(1, "%"+bid+"%");
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				ReviewBean rb = new ReviewBean();
				int rating= rs.getInt("rating");
				String name= rs.getString("name");
				String message = rs.getString("message");
				rb.setName(name);
				rb.setRating(rating);
				rb.setMessage(message);
				reviews.add(rb);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reviews;
	}
	
	
	
}
