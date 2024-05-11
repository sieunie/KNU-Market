package Gwp.KNUMarket.domain.product.presentation.exceptionHandler;

import Gwp.KNUMarket.domain.product.presentation.ProductController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = ProductController.class)
public class ProductExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> ProductNullPointerExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 사용자입니다.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> ProductNoSuchElementExceptionHandler() {
        return new ResponseEntity<>("존재하지 않는 상품입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> ProductNoPermissionExceptionHandler() {
        return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
    }
}
