package io.wisoft.avocadobackendhexagonal.domain.auth.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.LogoutUseCase;
import io.wisoft.avocadobackendhexagonal.global.jwt.JwtTokenProvider;
import io.wisoft.avocadobackendhexagonal.global.jwt.RedisJwtBlackList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogoutService implements LogoutUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisJwtBlackList redisJwtBlackList;

    @Override
    @Transactional
    public void logout(final String accessToken) {

        final String email = jwtTokenProvider.getSubject(accessToken);
        redisJwtBlackList.addToBlackList(email);
    }
}
