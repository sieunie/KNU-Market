package Gwp.KNUMarket.domain.user.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.user.presentation.AuthController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice(basePackageClasses = AuthController.class)
public class AuthExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> AuthNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> AuthIOExceptionHandler() {
        return new ResponseEntity<>("카카오 서버 인증 중에 문제가 발생했습니다.", HttpStatus.BAD_GATEWAY);
    }
}
