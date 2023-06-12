package io.wisoft.avocadobackendhexagonal.global.jwt;

import io.jsonwebtoken.*;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.token.ExpiredTokenException;
import io.wisoft.avocadobackendhexagonal.global.exception.token.InvalidTokenException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Log4j2
@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long validityInMilliseconds;
    private final StringRedisTemplate stringRedisTemplate;

    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") final String secretKey,
            @Value("${security.jwt.token.expire-length}") final long validityInMilliseconds,
            final StringRedisTemplate stringRedisTemplate) {

        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String createToken(final String subject) {
        final Claims claims = Jwts.claims().setSubject(subject);

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);
        log.info("now: {}", now);
        log.info("validity: {}", validity);


        final String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("생성된 JWT: {}", token);
        return token;
    }

    /**
     * 토큰에서 값 추출
     */
    public String getSubject(final String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 유효한 토큰인지 확인
     */
    public boolean validateToken(final String token) {
        try {

            /** 유효하지 않은 토큰일 경우 */
            if (stringRedisTemplate.opsForValue().get(token) == null) {
                throw new InvalidTokenException("유효하지 않은 토큰입니다.", ErrorCode.INVALID_TOKEN);
            }

            final Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            /** 만료시간이 지났을 경우 */
            if (claims.getBody().getExpiration().before(new Date())) {
                log.error("만료시간이 지난 토큰입니다.");
                throw new ExpiredTokenException("만료시간이 지난 토큰입니다.", ErrorCode.EXPIRED_TOKEN);
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            /* do nothing! */
            return false;
        }
    }
}

