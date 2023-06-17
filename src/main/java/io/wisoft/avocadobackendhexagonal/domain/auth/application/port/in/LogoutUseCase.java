package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

public interface LogoutUseCase {

    void logout(final String accessToken);
}
