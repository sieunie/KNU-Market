package Gwp.KNUMarket.domain.evaluation.application;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.NoPermissionException;

public interface EvaluationService {
    void post(User user, Product product);
    ResponseEntity<HttpStatus> patch(Integer id, Integer evaluationScore, Authentication authentication) throws NoPermissionException;
}
