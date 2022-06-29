package org.health.project.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.health.project.security.config.JwtConfig;
import org.health.project.security.entities.AppRole;
import org.health.project.security.entities.AppUser;
import org.health.project.security.service.SecurityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/")
@SecurityRequirement(name = "N7Therapy")

public class SecurityRestController {

    private SecurityService securityService;

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwt_token = request.getHeader(JwtConfig.AUTHORIZATION_HEADER); //Authorization: Bearer xxx

        if (jwt_token != null && jwt_token.startsWith(JwtConfig.TOKEN_HEADER_PREFIX)) { //Authorization: Bearer xxx

            try {
                String jwt = jwt_token.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JwtConfig.SECRET_PHRASE);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(jwt); // verify the token

                String username = decodedJWT.getSubject(); // get the username
                AppUser user = securityService.loadUserByUsername(username); // get the user using the username

                String jwtAccessToken = JWT.create() // create a new JWT with the username
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtConfig.ACCESS_TOKEN_EXPIRATION)) // 24hrs
                        .withIssuer(request.getRequestURL().toString()) // url of the issuer
                        .withClaim("roles", user.getRoles().stream().map(AppRole::getRoleName).collect(Collectors.toList())) // roles
                        .sign(algorithm);// secret

                Map<String, String> tokens = new HashMap<>();// create a map with the tokens
                tokens.put("access_token", jwtAccessToken);// access token
                tokens.put("refresh_token", jwt);// refresh token
                response.setContentType("application/json"); // set the content type
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception e) {
                throw e;
            }
        } else
            throw new RuntimeException(" You should refresh your token ");
    }


    @PreAuthorize("hasAuthority('USER')") // hasAuthority('USER')
    @GetMapping("/profile")
    public AppUser getUser(Principal principal) { // Principal is the user
        return securityService.loadUserByUsername(principal.getName());
    }
}
