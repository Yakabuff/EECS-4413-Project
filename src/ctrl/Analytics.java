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

import model.SIS;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/Analytics")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ANALYTICS_URL = "/Analytics.jspx";
	SIS model;
       
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
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		HttpSession session=request.getSession();
		String target = ANALYTICS_URL;		
		try {
			if(session.getAttribute("role") != "ADMIN")
				throw new Exception("Permission Denied!");
			try {
				String month = request.getParameter("month");
				List<String[]> results = model.getInstance().getMonthlyStats(Integer.parseInt(month));
				request.getSession().setAttribute("monthlyStats", results);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(ANALYTICS_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path == "Analytics/MonthlyReport") {
			
			
		}
	}

}
