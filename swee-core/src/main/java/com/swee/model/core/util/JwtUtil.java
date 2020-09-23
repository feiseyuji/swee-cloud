package com.swee.model.core.util;

import java.util.Map;
import java.util.Optional;
import com.swee.model.core.commonmodel.User;
import com.swee.model.core.constant.SignConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {

    private final static String secret = "swee-model";

    public static String encode(String key, Map<String,Object> param, String salt){
        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);

        jwtBuilder = jwtBuilder.setClaims(param);

        String token = jwtBuilder.compact();
        return token;

    }


    public  static Map<String,Object>  decode(String token ,String key,String salt){
        Claims claims=null;
        if (salt!=null){
            key+=salt;
        }
        try {
            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch ( JwtException e) {
           return null;
        }
        return  claims;
    }

    public static String token(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //获取claims
    private static Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static User user(String token) {
        Optional<Claims> claimsOpt = Optional.of(getClaims(token));
        if (claimsOpt.isPresent()) {
            Claims claims = claimsOpt.get();
            User user = User.builder().build();
            if (claims.get(SignConstant.USER_ID) != null) {
                user.setId(Long.valueOf(String.valueOf(claims.get(SignConstant.USER_ID))));
            }
            if (claims.get(SignConstant.PRINCIPAL) != null) {
                user.setMobile(String.valueOf(claims.get(SignConstant.PRINCIPAL)));
            }
            if (claims.get(SignConstant.NAME) != null) {
                user.setName(String.valueOf(claims.get(SignConstant.NAME)));
            }
            return user;
        }
        return null;
    }

}
