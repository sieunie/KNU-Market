package Gwp.KNUMarket.global.config.jwt;

import Gwp.KNUMarket.global.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;


@Component
public class JwtTokenProvider {

    private static final Long accessTokenValidTime = Duration.ofHours(2).toMillis();
    private static final Long refreshTokenValidTime = Duration.ofDays(7).toMillis();

    @Value("${jwt.secret-key}")
    private String secretKey;

    public Integer getUserId(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Integer.class);
    }

    public String getKakaoId(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userKakaoId", String.class);
    }

    public String getUserName(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userName", String.class);
    }

    public boolean isAccessToken(String token) throws MalformedJwtException{
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getHeader().get("type").toString().equals("access");
    }

    public String createAccessToken(User user){
        return createJwtToken(user,"access", accessTokenValidTime);
    }

    public String createRefreshToken(User user){
        return createJwtToken(user, "refresh", refreshTokenValidTime);
    }

    private String createJwtToken(User user, String type, Long tokenValidTime){
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("userKakaoId", user.getKakaoId());
        claims.put("userName", user.getName());

        return Jwts.builder()
                .setHeaderParam("type", type)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
