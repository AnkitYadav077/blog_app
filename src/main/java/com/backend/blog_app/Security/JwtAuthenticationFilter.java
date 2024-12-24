package com.backend.blog_app.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String requestToken = request.getHeader("Authorization");
            String username = null;
            String token = null;

            // Extract the token if it starts with "Bearer"
            if (requestToken != null && requestToken.startsWith("Bearer ")) {
                token = requestToken.substring(7); // Extract token after "Bearer "

                try {
                    username = this.jwtTokenHelper.getUsernameFromToken(token); // Get username from token
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT token: " + e.getMessage());
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT token has expired: " + e.getMessage());
                } catch (MalformedJwtException e) {
                    System.out.println("Invalid JWT token: " + e.getMessage());
                }
            } else {
                System.out.println("JWT token does not begin with Bearer");
            }

            // Validate the token and set the authentication in the context if valid
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    System.out.println("Invalid JWT token");
                }
            }
                     filterChain.doFilter(request,response);
        }

    }
