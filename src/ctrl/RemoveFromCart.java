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
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SIS model;
	private static final String SHOPPING_CART = "/ShoppingCart.jspx";
	private static final String HOME_URL = "/Home.jspx";
	private static final String SHOPPING_CART_URL = "/ShoppingCart.jspx";
	
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
    public RemoveFromCart() {
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
		System.out.println("removing ");
		System.out.println(request.getParameter("remove"));
		if(request.getParameter("remove") != null) {
			
			List<CartBookBean> cbb_in_cart = new ArrayList();
			CartBean cart = (CartBean) session.getAttribute("CART"); 
			String quantity = request.getParameter("quantity");
			String bid = request.getParameter("remove");
			
			cart.removeFromCart(bid);
			for(CartBookBean a : cart.getCBBInCart()) {
				cbb_in_cart.add(a);
			}
			session.setAttribute("BOOKS_IN_CART", cbb_in_cart);
			session.setAttribute("CART_SIZE", cart.numBooks());
			
			System.out.println("removing "+bid);
			request.getRequestDispatcher(SHOPPING_CART_URL).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
