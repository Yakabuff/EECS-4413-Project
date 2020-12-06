package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.SIS;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/UserHandler")
public class UserHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String SIGN_IN_URL = "/SignIn.jspx";
    private static final String HOME_URL = "/Home.jspx";
    private static final String PAYMENT_URL = "/Payment.jspx";
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
    public UserHandler() {
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
		String target = SIGN_IN_URL;
		if (request.getParameter("login") != null) {
			System.out.println("Log In Request Recieved");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserBean user = null;
			try {
				user = model.loginUser(email, password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (user != null) {
				session.setAttribute("currentUser", user);
				session.setAttribute("loggedIn", "true");
				System.out.println(user.getRole());
				if (user.getRole().contains("ADMIN")) {
					session.setAttribute("isAdmin", "true");
				}
				else {
					session.setAttribute("isAdmin", null);
				}
				if (request.getParameter("Payment") != null) {
					target = PAYMENT_URL;
				}
				else {
					target = HOME_URL;	
				}
			}
			else {
				request.setAttribute("outMsg", "Could not log in. Please check your credentials.");
			}
			
		}
		else if (request.getParameter("signup") != null) {
			System.out.println("Sign Up Request Recieved");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String street = request.getParameter("street");
			String province = request.getParameter("province");
			String country = request.getParameter("country");
			String phone = request.getParameter("phone");
			String zip = request.getParameter("zip");
			String city = request.getParameter("city");
			try {
				if (model.addUser(email, password, fname, lname, street, province, country, zip, phone, city)) {
					request.setAttribute("outMsg", "Signed up successfully! Sign in with your new account.");
				}
				else
				{
					request.setAttribute("outMsg", "Could not sign up. Account with email already exists.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (request.getParameter("logout") != null) {
			System.out.println("Log Out Request Recieved");
			session.setAttribute("currentUser", null);
			session.setAttribute("loggedIn", null);
			target = HOME_URL;
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
