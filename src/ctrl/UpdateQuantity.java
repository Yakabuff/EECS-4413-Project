package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CartBookBean;

/**
 * Servlet implementation class UpdateQuantity
 */
@WebServlet("/UpdateQuantity")
public class UpdateQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOPPING_CART_URL = "/ShoppingCart.jspx";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		String quantity = request.getParameter("quantity");
		List<CartBookBean> cbb_in_cart = new ArrayList();
		HttpSession session=request.getSession();
		CartBean cart = (CartBean) session.getAttribute("CART");
		
		cart.getCart().get(bid).setQuantity(Integer.parseInt(quantity));
		for(CartBookBean a : cart.getCBBInCart()) {
			cbb_in_cart.add(a);
		}
		session.setAttribute("BOOKS_IN_CART", cbb_in_cart);
		
		session.setAttribute("CART_SIZE", cart.numBooks());
		
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
