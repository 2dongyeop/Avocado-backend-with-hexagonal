package io.wisoft.avocadobackendhexagonal.global.interceptor;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.token.NotExistTokenException;
import io.wisoft.avocadobackendhexagonal.global.jwt.AuthorizationExtractor;
import io.wisoft.avocadobackendhexagonal.global.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor authExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    public BearerAuthInterceptor(
            final AuthorizationExtractor authExtractor,
            final JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler) {

        logger.info("interceptor.preHandle 호출");

        final String accessToken = authExtractor.extract(request, "Bearer");

        if (!StringUtils.hasText(accessToken)) {
            logger.error("토큰이 적재되지 않음");
            throw new NotExistTokenException("토큰이 적재되지 않음", ErrorCode.NOT_EXIST_TOKEN);
        }

        //토큰을 디코딩
        final String email = jwtTokenProvider.getSubject(accessToken);

        jwtTokenProvider.validateToken(email);

        //디코딩한 값으로 세팅
        request.setAttribute("email", email);
        return true;
    }
}