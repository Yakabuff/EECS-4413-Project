package rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.OrdersBean;
import model.SIS;

@Path("orders")
public class Orders {
	
@GET
@Path("/book/")
@Produces("text/plain")
public String getOrdersByBid(@QueryParam("bid") String bid) throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
	
	List<OrdersBean> orders = SIS.getInstance().getOrdersByBid(bid);
	
//	ObjectMapper om = new ObjectMapper();
//
//	return om.writeValueAsString(orders);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	int count = 0;
	String response = "[";
	for(OrdersBean o : orders) {
		if(count > 0) {
			response = response +", "+ gson.toJson(o);
		}else {
			response = response +" "+ gson.toJson(o);
		}
		
		count++;
	}
	response =response + "]";
	return response;
}

}
