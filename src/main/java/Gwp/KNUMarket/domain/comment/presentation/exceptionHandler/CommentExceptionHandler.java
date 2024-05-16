package Gwp.KNUMarket.domain.comment.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.comment.presentation.CommentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = CommentController.class)
public class CommentExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> CommentNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> CommentNoSuchElementExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 상품입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> CommentNoPermissionExceptionHandler() {
        return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
