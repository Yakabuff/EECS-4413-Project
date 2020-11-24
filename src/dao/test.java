package dao;

import java.sql.Connection;
import java.util.List;

import bean.BookBean;

public class test {
	public static void main(String args[]) {

		BookDAO bd = new BookDAO();
		
//		List<BookBean> list = bd.searchByTitle("Little Prince");
//		
//		System.out.println(list.get(0).getCategory());
		
		
		List<BookBean> list = bd.searchByBID("002");


	}
}
