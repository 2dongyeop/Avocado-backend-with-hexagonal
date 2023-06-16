package io.wisoft.avocadobackendhexagonal.global.jwt;

import io.wisoft.avocadobackendhexagonal.global.redis.RedisAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisJwtBlackList {

    @Value("${security.jwt.token.logout-length}")
    private int LOGOUT_TIME;

    private final RedisAdapter redisAdapter;
    private final String LOGOUT_STATUS = "LOGOUT_STATUS";


    public void addToBlackList(final String email) {

        if (isContained(email)) {
            redisAdapter.deleteValue(email);
        }

        redisAdapter.setValue(email, LOGOUT_STATUS, LOGOUT_TIME, TimeUnit.SECONDS);
        log.info("redis : {}을 로그아웃 처리합니다.", email);
    }

    public boolean isContained(final String email) {
        return redisAdapter.hasKey(email);
    }
}

