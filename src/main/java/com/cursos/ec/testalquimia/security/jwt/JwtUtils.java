package com.cursos.ec.testalquimia.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtils {

    private static final Logger LOGGER = LogManager.getLogger(JwtUtils.class);
    private final Integer HOURS_EXPIRATION_TOKEN = 2;

    @Value("${jwt.secret}")
    private String jwtSigningKey;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtSigningKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (MalformedJwtException e) {
            LOGGER.error("error token mal formado {}", token, e);

        } catch (UnsupportedJwtException e) {
            LOGGER.error("error token no soportada {}", token, e);
        } catch (ExpiredJwtException e) {
            LOGGER.error("error token expirado {}", token, e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("error token vacio {}", token, e);
        } catch (SecurityException e) {
            LOGGER.error("error token firma {}", token, e);
        } catch (JwtException e) {
            LOGGER.error("Error al procesar el token JWT: {}", token, e);
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);

    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {

        byte[] keyBytes = jwtSigningKey.getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(HOURS_EXPIRATION_TOKEN)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
