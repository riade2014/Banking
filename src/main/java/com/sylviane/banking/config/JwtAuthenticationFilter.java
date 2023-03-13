package com.sylviane.banking.config;

import com.sylviane.banking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private static final String AUTHORIZATION  = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain)
            throws ServletException, IOException {
         String authHeader = request.getHeader(AUTHORIZATION);
         String userEmail;
         String jwt;
        SecurityContextHolder.getContext().setAuthentication(null);
        if(authHeader == null || !authHeader.startsWith(BEARER)){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwt);
        if (userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userRepository.findByEmail(userEmail)
                    .orElseThrow(()-> new EntityNotFoundException("l'utilisateur introuvable lors de la validation du jwt"));
            if (jwtUtils.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
