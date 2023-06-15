package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.TokenResponse;
import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.token.InvalidTokenException;
import io.wisoft.avocadobackendhexagonal.global.jwt.AuthorizationExtractor;
import io.wisoft.avocadobackendhexagonal.global.jwt.JwtTokenProvider;
import io.wisoft.avocadobackendhexagonal.global.redis.RedisAdapter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JwtReIssueController {

    @Value("${security.jwt.token.token-type}")
    private String tokenType;

    private final RedisAdapter redisAdapter;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthorizationExtractor extractor;

    @GetMapping("/jwt/re-issuance")
    public ResponseEntity<TokenResponse> reIssueAccessToken(final HttpServletRequest request) {

        final String refreshToken = extractor.extract(request, tokenType);
        final String email = jwtTokenProvider.getSubject(refreshToken);


        if (!redisAdapter.hasKey(email)) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다", ErrorCode.INVALID_TOKEN);
        }

        final String reIssuedAccessToken = jwtTokenProvider.createAccessToken(email);

        log.info("{}님에게 accessToken {}을 재발급합니다.", email, reIssuedAccessToken);
        return ResponseEntity.ok(new TokenResponse(tokenType, reIssuedAccessToken, refreshToken));
    }
}
