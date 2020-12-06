package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class Analytics
 */
@WebServlet("/Analytics")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ANALYTICS = "/Analytics.jspx";
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
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		Map<String, Integer> stats = model.getPurchasesForAllMonths();
		HttpSession session=request.getSession();
		UserBean user = (UserBean) session.getAttribute("currentUser");
		
		if(user != null && user.getRole().equals("ADMIN")) {
			System.out.println("Entering analytics page");
			System.out.println(user.getRole());
			System.out.println(stats.get("DECEMBER"));
			request.setAttribute("MONTHS", stats);
			request.setAttribute("IS_ADMIN", true);
			request.getRequestDispatcher(ANALYTICS).forward(request, response);
		}else if(user == null || !user.getRole().equals("ADMIN")){
			request.setAttribute("IS_ADMIN", false);
			request.getRequestDispatcher(ANALYTICS).forward(request, response);
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
