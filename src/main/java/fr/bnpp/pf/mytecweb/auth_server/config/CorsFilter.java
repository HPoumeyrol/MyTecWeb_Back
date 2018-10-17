package fr.bnpp.pf.mytecweb.auth_server.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {

	@Value( "${client.url}" )
    private  String clientUrl;

	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,	FilterChain filterChain) throws ServletException, IOException {
		
		System.err.println("--- cors filter START----");
		System.err.println("Method : " + httpServletRequest.getMethod());
		System.err.println("RequestURL : " + httpServletRequest.getRequestURL());
		System.err.println("QueryString : " + httpServletRequest.getQueryString());
		
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = httpServletRequest.getHeader(headerName);
			System.err.println("Header Name:  " + headerName + " = " + headerValue);
			
		}
			
		
		//httpServletResponse.addHeader("Access-Control-Allow-Origin", clientUrl);
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "http://mytecweb.ddns.net:4200");
				httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept,x-req,  Authorization");
		
				
		System.err.println("clienturl = " + clientUrl);
		System.err.println("Response Headers : " + httpServletResponse.getHeaderNames());
		System.err.println("Response Headers: " + httpServletResponse.getHeaders("Access-Control-Allow-Origin"));
		
		
		System.err.println("--- cors filter END ----");
		System.err.println("");
		
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}

