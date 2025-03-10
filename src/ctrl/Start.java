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

import bean.BookBean;
import model.SIS;


/**
 * Servlet implementation class BookStore
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
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
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BookBean>results = new ArrayList<BookBean>();
		
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		
		String category = request.getParameter("categories");
		String title = request.getParameter("titles");
		String review = request.getParameter("review");
		if(category != null) {
			
			System.out.println(category);
			results =model.retrieveFromBookCategory(category);
			request.setAttribute("RESULTS", results);
			request.setAttribute("BOOK_NUM", results.size());
			
		}else if(title != null) {
			
			System.out.println(title);
			results =model.retrieveFromBookTitle(title);
			request.setAttribute("RESULTS", results);
			request.setAttribute("BOOK_NUM", results.size());

		}
		
		
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
