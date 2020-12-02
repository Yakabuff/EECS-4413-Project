package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bean.BookBean;
import model.SIS;

@Path("catalog")
public class Catalog {
	
@GET
@Path("/book/")
@Produces(MediaType.APPLICATION_JSON)
public BookBean getBookInfo(@QueryParam("bid") String bid) throws ClassNotFoundException {
	BookBean book = SIS.getInstance().retrieveFromBookID(bid);
	return book;
	
}

}
