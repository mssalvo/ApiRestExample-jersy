package it.ms.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;

/**
 * @author m.salvo salvo.mariniello@gmail.com
 **/

@Path("/")
public class ApiInformation {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response infoApi(@HeaderParam("authorization") String authString) throws JSONException {
 
     return Response.status(200).entity("{\"info\":\"MS Api Init\"}").build();
 
	}
	
}
