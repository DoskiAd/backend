package io.shifu.doskiad.security.filters;

import io.shifu.doskiad.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        String token = request.getParameter("token");

        if (token == null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        } else {
            TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}