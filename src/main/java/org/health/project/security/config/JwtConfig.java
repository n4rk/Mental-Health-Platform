package org.health.project.security.config;

public class JwtConfig {
    public static String SECRET_PHRASE = "SECRET_PHRASE";
    public static int ACCESS_TOKEN_EXPIRATION = 24*60*60*1000; // 24hrs
    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String TOKEN_HEADER_PREFIX = "Bearer ";
    public static String REFRESH_PATH = "/refresh-token";
    public static int REFRESH_TOKEN_EXPIRATION = 48*60*60*1000; // 48hrs
}
