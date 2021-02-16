package ru.otus.spring.exception;

/**
 * @author MTronina
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.otus.spring.dto.ApplicationError;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApplicationError> exception(final Throwable throwable) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApplicationError(errorMessage));
    }

    @ExceptionHandler({UserNotFoundException.class,
                       RoleNotFoundException.class})
    public ResponseEntity<ApplicationError> exceptionNotFoundException(final Throwable throwable) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApplicationError(errorMessage));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationError> exceptionApplication(final Throwable throwable) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApplicationError(errorMessage));
    }
}
