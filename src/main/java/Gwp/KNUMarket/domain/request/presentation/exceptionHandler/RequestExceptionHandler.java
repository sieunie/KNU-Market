package Gwp.KNUMarket.domain.request.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.request.presentation.RequestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = RequestController.class)
public class RequestExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> RequestNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> RequestNoSuchElementExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 컨텐츠입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> RequestNoPermissionExceptionHandler() {
        return new ResponseEntity<>("수정 또는 삭제 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }
}

