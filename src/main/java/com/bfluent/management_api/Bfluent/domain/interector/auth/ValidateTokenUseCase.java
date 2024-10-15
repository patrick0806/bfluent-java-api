package com.bfluent.management_api.Bfluent.domain.interector.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenUseCase {

    @Value("${spring-store.jwt.secret}")
    private String secret;

    public String execute(String token){
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("bfluent")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

}
