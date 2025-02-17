package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AuthFilter",
        urlPatterns = "/user/*",
        servletNames = {"LoginServlet", "LogoutServlet"},
        dispatcherTypes = DispatcherType.REQUEST)
public class AuthFilter implements Filter {
    //write your code here!

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);



        if(session == null){
            res.sendRedirect("/login.jsp");
        } else if(session.getAttribute("user")==null) {
            session.invalidate();
            res.sendRedirect("/login.jsp");
        } else {
            // pass the request along the filter chain
            filterChain.doFilter(req, res);
        }

    }

}