package fr.bnpp.pf.mytecweb.auth_server.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        
    	System.err.println("--- JwtAuthenticationTokenFilter filter----");
    	
	Enumeration<String> headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()) {
		String headerName = headerNames.nextElement();
		String headerValue = request.getHeader(headerName);
		System.err.println("Header Name:  " + headerName + " = " + headerValue);
		
	}
	System.err.println("Method : " + request.getMethod());
	System.err.println("QueryString : " + request.getQueryString());
	
	System.err.println("--------------------");
	System.err.println("");
    	
	
    	String authToken = request.getHeader(this.tokenHeader);
        // authToken.startsWith("Bearer ")
        // String authToken = header.substring(7);

        if(authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }

        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        logger.info("checking authentication for user " + username);

        if (username != null ) {
	        
	        	if (SecurityContextHolder.getContext().getAuthentication() == null) {
		
		            // It is not compelling necessary to load the user details from the database. You could also store the information
		            // in the token and read it from it. It's up to you ;)
		            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		            
		            logger.info("UserDetails : " + userDetails.getUsername());
		
		            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
		            // the database compellingly. Again it's up to you ;)
		            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
		                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                logger.info("authenticated user " + username + ", setting security context");
		                SecurityContextHolder.getContext().setAuthentication(authentication);
		            } else {
		            		logger.error("Invalid token !");
		            }
	        	} else {
	        		logger.info( "SecurityContextHolder.getContext().getAuthentication()  = " + SecurityContextHolder.getContext().getAuthentication()  );
	        	}
        	
        } else {
        	
        		logger.error("Invalid user !");
        }

        chain.doFilter(request, response);
    }
}