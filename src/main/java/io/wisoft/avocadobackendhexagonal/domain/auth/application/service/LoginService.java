package io.wisoft.avocadobackendhexagonal.domain.auth.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.LoginUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.LoginCommand;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.config.bcrypt.EncryptHelper;
import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.Illegal.CustomIllegalException;
import io.wisoft.avocadobackendhexagonal.global.jwt.JwtTokenProvider;
import io.wisoft.avocadobackendhexagonal.global.redis.RedisAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final int LOGIN_EXPIRED_TIME = 1 * 60 * 60; //1 hour

    private final LoadStaffPort loadStaffPort;
    private final LoadMemberPort loadMemberPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisAdapter redisAdapter;
    private final EncryptHelper encryptHelper;

    @Override
    @Transactional
    public String loginMember(final LoginCommand command) {

        final Member member = loadMemberPort.findByEmail(command.email());
        validatePassword(command, member.getPassword());

        final String token = jwtTokenProvider.createToken(member.getNickname());
        log.info("{}님이 로그인 하셨습니다.", member.getNickname());

        saveRedis(token, member.getNickname());
        log.info("redis : 토큰 {}을 1시간 동안 저장합니다.", token);

        return token;
    }

    @Override
    @Transactional
    public String loginStaff(final LoginCommand command) {

        final Staff staff = loadStaffPort.findByEmail(command.email());
        validatePassword(command, staff.getPassword());

        final String token = jwtTokenProvider.createToken(staff.getName());
        log.info("{}님이 로그인 하셨습니다.", staff.getName());

        saveRedis(token, staff.getName());
        log.info("redis : 토큰 {}을 1시간 동안 저장합니다.", token);

        return token;
    }


    private void saveRedis(final String token, final String name) {
        redisAdapter.setValue(
                token,
                name,
                LOGIN_EXPIRED_TIME,
                TimeUnit.SECONDS
        );
    }

    private void validatePassword(final LoginCommand command, final String hashedPassword) {
        if (!encryptHelper.isMatch(command.password(), hashedPassword)) {
            throw new CustomIllegalException("password is invalid", ErrorCode.INVALID_PASSWORD);
        }
    }

}
