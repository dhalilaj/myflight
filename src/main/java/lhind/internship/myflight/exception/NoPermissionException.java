package lhind.internship.myflight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "No permissions to perform this action.")
public class NoPermissionException extends RuntimeException {

    public NoPermissionException() {
        super("No permissions to perform this action.");
    }
}
