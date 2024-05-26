package Gwp.KNUMarket.domain.evaluation.application.impl;

import Gwp.KNUMarket.domain.evaluation.application.EvaluationService;
import Gwp.KNUMarket.global.data.entity.Evaluation;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;

    @Override
    public void post(User user, Product product) {
        Evaluation evaluation = Evaluation.builder()
                .user(user)
                .product(product)
                .build();

        evaluationRepository.save(evaluation);
    }
}
