package io.wisoft.avocadobackendhexagonal.global.config.bcrypt;

public interface EncryptHelper {
    String encrypt(final String password);
    boolean isMatch(final String password, final String hashed);
}
