package Gwp.KNUMarket.domain.evaluation.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.evaluation.presentation.EvaluationController;
import Gwp.KNUMarket.domain.request.presentation.RequestController;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = EvaluationController.class)
public class EvaluationExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> EvaluationNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> EvaluationNoSuchElementExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 컨텐츠입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> EvaluationNoPermissionExceptionHandler() {
        return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> EvaluationIndexOutOfBoundsExceptionHandler() {
        return new ResponseEntity<>("평가 점수 범위를 초과했습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> EvaluationSQLIntegrityConstraintViolationExceptionHandler() {
        return new ResponseEntity<>("중복된 요청입니다.", HttpStatus.CONFLICT);
    }
}

