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
import bean.ReviewBean;
import model.SIS;

/**
 * Servlet implementation class BookDetails
 */
@WebServlet("/BookDetails")
public class BookDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOK_DETAILS_URL = "/BookDetails.jspx";
	
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
    public BookDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookBean results;
		
		ServletContext context = getServletContext();
		model = (SIS) context.getAttribute("model");
		
		String bid = request.getParameter("bookdetails");
		if(bid != null) {
			results =model.retrieveFromBookID(bid);
			request.setAttribute("RESULTS", results);
			request.setAttribute("BOOK_NUM", 1);
		//add ratings
			List<ReviewBean> ratings = new ArrayList();
			ratings = model.getReview(bid);
			request.setAttribute("REVIEWS", ratings);
			request.setAttribute("REVIEW_NUM", ratings.size());
		}
		
		request.getRequestDispatcher(BOOK_DETAILS_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
