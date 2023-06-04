package io.wisoft.avocadobackendhexagonal.global.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class CustomNotFoundException extends RuntimeException {
}
