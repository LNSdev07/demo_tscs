package com.example.bedemonosql.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.bedemonosql.entity.User;
import com.example.bedemonosql.security.userprincal.UserCustomDetail;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service
@Slf4j
public class JwtProvider  {
    public  String createToken(User userDTO){
        String userDTOJson = new Gson().toJson(userDTO);
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create().withClaim("user", userDTOJson)
                .withExpiresAt(new Date(System.currentTimeMillis()+ (86400000L)))
                .sign(algorithm);

        return access_token;
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUsernameFromToken(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String userDTOJson = decodedJWT.getClaim("user").asString();
        User user = new Gson().fromJson(userDTOJson, User.class);
        String username = user.getUsername();

        return username;
    }

    public Boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
