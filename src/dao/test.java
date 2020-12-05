package dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import bean.BookBean;
import bean.OrdersBean;
import bean.ReviewBean;
import model.SIS;

public class test {
	public static void main(String args[]) {

		BookDAO bd = new BookDAO();
		ReviewDAO rd = new ReviewDAO();
		PurchaseOrderDAO pod= new PurchaseOrderDAO();
		AnalyticsDAO ad = new AnalyticsDAO();
		SIS model = new SIS();
//		List<BookBean> list = bd.searchByTitle("Little Prince");
//		
//		System.out.println(list.get(0).getCategory());
//		
//		BookBean b1 = bd.searchByBID("002");
//		System.out.println(b1.getTitle());
		
		
//		BookBean b = model.retrieveFromBookID("002");
//		System.out.println(b.getTitle());
		
		
//		rd.addReview(5, "jonathan", "b002");
//		List<ReviewBean> list = rd.getReviews("b001");
//		System.out.println(list.get(0).getName());
//		System.out.println(list.get(1).getName());
//		System.out.println(list.get(0).getRating());
//		System.out.println(list.get(1).getRating());

//		pod.getRow(4);
//		List<OrdersBean> orders = pod.getPurchaseOrderByBid("b001");
		Map<String, Integer> a = ad.getPurchasesForAllMonths();

	}
}
