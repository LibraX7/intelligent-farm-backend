package com.sipc.intelligentfarmbackend.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sipc.intelligentfarmbackend.pojo.domain.User;

import java.util.Date;

public class JwtUtils {
    public static final long EXPIRE_TIME = (long) 1000 * 60 * 60 * 24 * 15;
    public static final String SECRET = "IntelligentFarm";
    public static String sign(User user) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("id",user.getId())
                .withClaim("phone",user.getPhone())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
    }
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static User getUserByToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        User user = new User();
        user.setId(decodedJWT.getClaim("id").asInt());
        user.setPhone(decodedJWT.getClaim("phone").asString());
        return user;
    }
}
