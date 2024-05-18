package Gwp.KNUMarket.domain.request.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface RequestService {
    ResponseEntity<HttpStatus> post(Integer productId, Authentication authentication);
}
