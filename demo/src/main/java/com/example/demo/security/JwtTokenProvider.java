
package com.example.demo.security;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    // Método para crear el token
    public String createToken(String username, Integer userId, String nombreCompleto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("nombreCompleto", nombreCompleto)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    // Método para validar el token
    public boolean validateToken(String token) {
        try {
             
            // Usamos parserBuilder para analizar JWT firmado
            Jwts.parser()
                    .setSigningKey(secretKey) // Establecemos la clave para verificar la firma
                    .build()
                    .parseClaimsJws(token); // Validamos el JWT firmado
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect", e);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Expired JWT token.", e);
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token.", e);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.", e);
        }
    }

    // Método para obtener el username del token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Obtenemos el "subject", que es el nombre de usuario
    }

     // Método para obtener la clave secreta desde las propiedades
     private SecretKey getSigningKey() {
        byte[] secretBytes = secretKey.getBytes();  // Convertir la clave secreta en bytes
        return new SecretKeySpec(secretBytes, 0, secretBytes.length, "HmacSHA256");  // Crear un SecretKey con HMACSHA256
    }
}
