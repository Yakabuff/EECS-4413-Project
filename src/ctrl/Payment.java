package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AddressBean;
import bean.CartBookBean;
import bean.UserBean;
import dao.DAOConnect;
import model.SIS;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_URL = "/UserHandler?Payment=True";
	private static final String PAYMENT_URL = "/Payment.jspx";
	SIS model;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		HttpSession session=request.getSession();
		String target = PAYMENT_URL;
		if (session.getAttribute("loggedIn") != null) {
			UserBean user = (UserBean) session.getAttribute("currentUser");
			AddressBean addr;
			if (request.getParameter("payBTN") != null) {
				System.out.println(request.getParameter("shipping_address"));
				if (request.getParameter("shipping_address").contains("false")) {
					addr = user.getAddressBean();
				}
				else {
					String street = request.getParameter("street");
					String province = request.getParameter("province");
					String country = request.getParameter("country");
					String phone = request.getParameter("phone");
					String zip = request.getParameter("zip");
					String city = request.getParameter("city");
					addr = new AddressBean (street, province, country, zip, phone, city, 0);
				}
				Boolean payment_bool;
				try {
					payment_bool = model.processPayment(user,addr, (List<CartBookBean>) session.getAttribute("BOOKS_IN_CART"));
					if (payment_bool) {
						request.setAttribute("approveMsg", "Payment Processed! Your Order has been submitted.");
						context.setAttribute("JUST_PURCHASED", session.getAttribute("BOOKS_IN_CART"));
						session.removeAttribute("BOOKS_IN_CART");
						session.removeAttribute("CART");
					}
					else {
						request.setAttribute("rejectMsg", "Credit Card Authorization Failed.");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("rejectMsg", "Credit Card Authorization Failed.");
				}
				
			}
		}
		else {
			target = SIGNIN_URL;
		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
