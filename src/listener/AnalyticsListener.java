package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import bean.BookBean;
import bean.CartBookBean;
import model.SIS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class AnalyticsListener implements ServletContextAttributeListener {
	public static  List<String> topBooks;
	SIS model;
    /**
     * Default constructor. 
     */
    public AnalyticsListener() {
     
    	topBooks = new ArrayList();
    	try {
			model = SIS.getInstance();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
    }


    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	attributeReplaced(arg0);
    }


    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }


    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	if(arg0.getName().equals("JUST_PURCHASED")) {
    		
    		List<CartBookBean> cart = (List<CartBookBean>) arg0.getValue();
    		
    		//write top books to db if not exist
    		//else increment by 1
    		for(CartBookBean cbb : cart) {
    			if(model.getAnalyticsDAO().bookExists(cbb.getBook().getBid())) {
    				model.getAnalyticsDAO().incrementABook(cbb.getBook().getBid());
    			}else {
    				model.getAnalyticsDAO().addNewBook(cbb.getBook().getBid());
    			}
    		}
    		topBooks = model.getAnalyticsDAO().getTopBooks();
    	}
    }


	
}
