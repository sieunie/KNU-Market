package Gwp.KNUMarket.domain.user.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.user.presentation.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> UserSQLIntegrityConstraintViolationException() {
        return new ResponseEntity<>("중복인 닉네임 입니다.", HttpStatus.CONFLICT);
    }}
