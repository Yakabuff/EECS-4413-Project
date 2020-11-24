package dao;

import java.sql.Connection;
import java.util.List;

import bean.ReviewBean;

public class ReviewDAO {
	
	Connection con;
	
	public ReviewDAO() {
		con = DAOConnect.getConnection();
	}
	
	public void addReview(int stars) {
		
		
	}
	
	public List getReviews(){
		return null;
	}
	
	
	
}
