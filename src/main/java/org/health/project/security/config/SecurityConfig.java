package org.health.project.security.config;

import lombok.AllArgsConstructor;
import org.health.project.security.filters.JwtAuthenticationFilter;
import org.health.project.security.filters.JwtAuthorizationFilter;
import org.health.project.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl detailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( detailsService );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// csrf protection disabled
        http.formLogin().disable().cors(httpSecurityCorsConfigurer -> {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod("GET");
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/**",  corsConfiguration);
            httpSecurityCorsConfigurer.configurationSource(source);
        });

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers(
                "/login/**",
                "/swagger-ui**",
                "/swagger-ui/**",
                "/**",
                JwtConfig.REFRESH_PATH).permitAll();
        http.addFilter( new JwtAuthenticationFilter( authenticationManagerBean() ) );
        http.addFilterBefore( new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
