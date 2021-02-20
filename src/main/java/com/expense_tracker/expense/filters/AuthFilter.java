package com.expense_tracker.expense.filters;

import com.expense_tracker.expense.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.netty.handler.codec.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");

        if(authHeader != null){
            String[] authHeaderArr = authHeader.split("Bearer");
            if(authHeaderArr.length > 1 && authHeaderArr[1] != null){
                String token = authHeaderArr[1];
                try {
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                }catch (Exception e){
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired token");
                    return;
                }
            }else {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization header must be Bear token[]");
                return;
            }
        }else{
            httpResponse.sendError(HttpStatus.NO_CONTENT.value(), "Authorization header must be present");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
