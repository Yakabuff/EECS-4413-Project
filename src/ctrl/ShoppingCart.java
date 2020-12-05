package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CartBookBean;
import model.SIS;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	
	private static final String SHOPPING_CART_URL = "/ShoppingCart.jspx";

	private static final long serialVersionUID = 1L;
	
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
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CartBookBean> cbb_in_cart = new ArrayList();
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		
		HttpSession session=request.getSession();
		CartBean cart = (CartBean) session.getAttribute("CART");
		if(cart != null) {
			for(CartBookBean a : cart.getCBBInCart()) {
				cbb_in_cart.add(a);
			}
			session.setAttribute("BOOKS_IN_CART", cbb_in_cart);
			session.setAttribute("CART_SIZE", cart.numBooks());
		}else {
			session.setAttribute("CART_SIZE", 0);
		}
		int total_price = 0;
		if(session.getAttribute("BOOKS_IN_CART") != null) {
			for(CartBookBean cbb : cbb_in_cart) {
				total_price = cbb.getQuantity()*cbb.getBook().getPrice();
			}
			session.setAttribute("TOTAL_PRICE", total_price);
		}else {
			session.setAttribute("TOTAL_PRICE", total_price);
		}

		request.getRequestDispatcher(SHOPPING_CART_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
