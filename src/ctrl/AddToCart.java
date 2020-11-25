package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BookBean;
import bean.CartBean;
import bean.CartBookBean;
import model.SIS;



/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String HOME_URL = "/Home.jspx";
	
	SIS model;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		SIS model;
		try {
			model = SIS.getInstance();
			context.setAttribute("model", model);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");

		HttpSession session=request.getSession();
		
		CartBean cart = (CartBean) session.getAttribute("CART"); 
		System.out.println(request.getParameter("addtocart"));
		BookBean book = model.retrieveFromBookID(request.getParameter("addtocart"));
		
		CartBookBean cbb = new CartBookBean(1, book);
		
		if(cart == null) {
			
			cart = new CartBean();
			cart.addToCart(cbb);
			System.out.println("Cart has # books: " + cart.numBooks());
		}else {
			cart.addToCart(cbb);
			System.out.println("Cart has # books: " + cart.numBooks());
		}
		
		
		
		session.setAttribute("CART", cart);
		
		request.getRequestDispatcher(HOME_URL).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
