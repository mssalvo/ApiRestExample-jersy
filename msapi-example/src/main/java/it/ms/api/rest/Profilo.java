package it.ms.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

@Path("/profilo-util")
public class Profilo {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();
	 
		jsonObject.put("F-Value", 120);
		jsonObject.put("C-Value", 158);

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/code-email/{type}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response codeEmail(@PathParam("type") String type, @PathParam("email") String email) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("Type-Value", type);
		jsonObject.put("Email-Value", email);

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/code-telefono/{type}/{telefono}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response codeTelefono(@PathParam("type") String type, @PathParam("telefono") String telefono) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("Type-Value", type);
		jsonObject.put("Telefono-Value", telefono);

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}

}
