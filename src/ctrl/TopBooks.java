package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import listener.AnalyticsListener;
import model.SIS;

/**
 * Servlet implementation class TopBooks
 */
@WebServlet("/TopBooks")
public class TopBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String TOP_BOOKS = "TopBooks.jsp";
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
    public TopBooks() {
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
//		if(AnalyticsListener.topBooks!=null) {
//			response.getWriter().append(new Gson().toJson(AnalyticsListener.topBooks));
//		}else if(AnalyticsListener.topBooks.size()==0){
//			response.getWriter().append(new Gson().toJson(model.getAnalyticsDAO().getTopBooks()));
//		}
		System.out.println(AnalyticsListener.topBooks.isEmpty());
		request.getRequestDispatcher(TOP_BOOKS).forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append(new Gson().toJson(model.getAnalyticsDAO().getTopBooks()));
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		  PrintWriter pw = response.getWriter();
		  if(AnalyticsListener.topBooks.isEmpty()) {
			  pw.println(new Gson().toJson(model.getAnalyticsDAO().getTopBooks()));
		  }else {
			  response.getWriter().append(new Gson().toJson(AnalyticsListener.topBooks));
		  }
		  
	}

}
