package com.academy.pingiz.security;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleAuthFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(SimpleAuthFilter.class);

    public SimpleAuthFilter() {
        log.info("Initiated SimpleAuthFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String loginURL = request.getContextPath() + "/login";
        boolean loginRequest = loginURL.equals(request.getRequestURI()) ||
                request.getContextPath().equalsIgnoreCase(request.getRequestURI()) ||
                request.getRequestURI().equalsIgnoreCase("/");

        HttpSession session = request.getSession();
        if(loginRequest){
            chain.doFilter(request, response);
        } else if(session==null){
            response.sendRedirect("/");
        }  else if (session.getAttribute("validated") == null  ){
            session.setAttribute("validated", false);
            response.sendRedirect("/login");
        }   else if (session.getAttribute("validated") != null && !(boolean)session.getAttribute("validated") ){
            response.sendRedirect("/login");
        }else if (session.getAttribute("validated") != null && (boolean)session.getAttribute("validated") ){
            chain.doFilter(request, response);
        }

//        if (request.getSession(false) == null) {
//            response.sendRedirect("login");
//        } else if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
//            response.sendRedirect("logout");
//        } else {
//            chain.doFilter(request, response);
//        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }



    @Override
    public void destroy() {
    }

}