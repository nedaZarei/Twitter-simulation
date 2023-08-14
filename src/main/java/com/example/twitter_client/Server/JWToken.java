package com.example.twitter_client.Server;

import java.time.LocalDateTime;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWToken {
    public static String CreateJWT(String username) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("1989");
            token = JWT.create()
                    .withClaim("Exp", LocalDateTime.now().plusDays(1).toString())
                    .withClaim("username", username)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public static String VerifyJWT(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("1989");
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            decodedJWT = verifier.verify(token);

            LocalDateTime expirationTime = LocalDateTime.parse(decodedJWT.getClaim("Exp").asString());
            LocalDateTime currentTime = LocalDateTime.now();

            if (currentTime.isBefore(expirationTime)) {
                return decodedJWT.getClaims().get("username").asString();
            } else {
                return "-1";
            }
        } catch (JWTVerificationException exception) {
            return "-1";
        }
    }
}
