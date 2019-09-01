package it.ms.api.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ms.api.util.ApplicationProperty;
import sun.misc.BASE64Decoder;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String authString = req.getHeader("authorization");
		this.context.log("Requested authString::" + authString);

		String uri = req.getRequestURI();
		String pathInfo = req.getPathInfo();
		this.context.log("Requested Resource::" + uri + " pathInfo: " + req.getPathInfo());

		if (pathInfo == null || pathInfo != null && pathInfo.length() < 2) {
			chain.doFilter(request, response);
		} else {
			if (isAuthenticated(authString)) {
				System.out.println("AUTENTICATED");
				chain.doFilter(request, response);
			} else {
				System.out.println("NOT AUTENTICATED");
				res.setContentType("application/json");
				PrintWriter out = res.getWriter();
				out.println("{\"error\":\"User not authenticated\"}");
			}
		}

	}

	private boolean isAuthenticated(String authString) {
		if (authString == null)
			return false;

		String decodedAuth = "", user = "", password = "";

		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];

		byte[] bytes = null;

		try {

			bytes = new BASE64Decoder().decodeBuffer(authInfo);
			decodedAuth = new String(bytes);
			String[] authenticationParts = decodedAuth.split(":");
			user = authenticationParts[0];
			password = authenticationParts[1];
			
			//System.out.println("userAuth: [" + user + "] passwordAuth: [" + password + "]");

			// Fai qualcosa...
			return AuthenticatedTables.isPresent(user, password);

		} catch (IOException e) {
			this.context.log("IOException: ", e);
			return false;
		} catch (Exception ex) {
			this.context.log("Exception: ", ex);
			return false;
		}

	}

	public void destroy() {

	}

}
