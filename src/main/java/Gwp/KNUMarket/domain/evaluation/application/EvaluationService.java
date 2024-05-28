package Gwp.KNUMarket.domain.evaluation.application;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.NoPermissionException;

public interface EvaluationService {
    ResponseEntity<HttpStatus> post(Integer alarmId, Integer evaluationScore, Authentication authentication) throws NoPermissionException;
}
