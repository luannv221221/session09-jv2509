package com.ra.security.jwt;


import com.ra.security.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt_secret_key}")
    private String jwtSecretKey;
    @Value("${jwt_expired}")
    private int jwtExpired;

    private Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    // tao ra token
    public String generateToken(UserPrinciple userPrinciple) {
        Date dateExpiration = new Date(System.currentTimeMillis() + jwtExpired * 1000);
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey).
                setExpiration(dateExpiration).
                compact();
    }
    // validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
    // lay du lieu tu token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
