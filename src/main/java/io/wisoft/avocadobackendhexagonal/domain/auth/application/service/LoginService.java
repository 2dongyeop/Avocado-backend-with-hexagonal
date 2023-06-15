package io.wisoft.avocadobackendhexagonal.domain.auth.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.LoginUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.LoginCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.TokenCommand;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    @Value("${security.jwt.token.refresh-expire-length}")
    private long REFRESH_TOKEN_EXPIRE_SECOND;

    @Value("${security.jwt.token.token-type}")
    private String tokenType;

    private final LoadStaffPort loadStaffPort;
    private final LoadMemberPort loadMemberPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisAdapter redisAdapter;
    private final EncryptHelper encryptHelper;

    @Override
    @Transactional
    public TokenCommand loginMember(final LoginCommand command) {

        final Member member = loadMemberPort.findByEmail(command.email());
        validatePassword(command, member.getPassword());

        final String accessToken = jwtTokenProvider.createAccessToken(member.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail());

        redisAdapter.setValue(member.getEmail(), refreshToken, REFRESH_TOKEN_EXPIRE_SECOND, TimeUnit.SECONDS);

        log.info("redis : {} 님의 리프레쉬 토큰 {} 을 1시간동안 저장합니다.", member.getEmail(), accessToken);
        return new TokenCommand(tokenType, accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenCommand loginStaff(final LoginCommand command) {

        final Staff staff = loadStaffPort.findByEmail(command.email());
        validatePassword(command, staff.getPassword());

        final String accessToken = jwtTokenProvider.createAccessToken(staff.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken(staff.getEmail());

        redisAdapter.setValue(staff.getEmail(), refreshToken, REFRESH_TOKEN_EXPIRE_SECOND, TimeUnit.SECONDS);

        log.info("redis : {}님의 리프레쉬 토큰{}을 1시간동안 저장합니다.", staff.getEmail(), accessToken);
        return new TokenCommand(tokenType, accessToken, refreshToken);
    }


    private void validatePassword(final LoginCommand command, final String hashedPassword) {
        if (!encryptHelper.isMatch(command.password(), hashedPassword)) {
            throw new CustomIllegalException("password is invalid", ErrorCode.INVALID_PASSWORD);
        }
    }
}
