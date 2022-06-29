package org.health.project.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.health.project.security.config.JwtConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(JwtConfig.REFRESH_PATH)) { // refresh token
            filterChain.doFilter(request, response);// continue
            return;
        }
        String jwt_token = request.getHeader(JwtConfig.AUTHORIZATION_HEADER); //Authorization: Bearer xxx
        if (jwt_token != null && jwt_token.startsWith(JwtConfig.TOKEN_HEADER_PREFIX)) { // does it have a passport, jwt will take care of it
            try {
                Algorithm algorithm = Algorithm.HMAC256(JwtConfig.SECRET_PHRASE); // secret phrase
                JWTVerifier verifier = JWT.require(algorithm).build(); // build the verifier
                String jwt = jwt_token.substring(7); // remove "Bearer "
                DecodedJWT decodedJWT = verifier.verify(jwt); // verify the token

                String username = decodedJWT.getSubject(); // get the username
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);// get the roles
                Collection<GrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());// convert to authorities
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            filterChain.doFilter(request, response); // spring security authorization
        }
    }
}
