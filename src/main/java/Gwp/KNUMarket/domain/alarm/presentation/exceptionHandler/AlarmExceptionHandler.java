package Gwp.KNUMarket.domain.alarm.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.alarm.presentation.AlarmController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = AlarmController.class)
public class AlarmExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> AlarmNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> AlarmNoSuchElementExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 컨텐츠입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> AlarmNoPermissionExceptionHandler() {
        return new ResponseEntity<>("삭제 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
