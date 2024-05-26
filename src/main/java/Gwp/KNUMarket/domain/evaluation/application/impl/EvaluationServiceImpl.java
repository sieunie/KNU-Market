package Gwp.KNUMarket.domain.evaluation.application.impl;

import Gwp.KNUMarket.domain.evaluation.application.EvaluationService;
import Gwp.KNUMarket.global.data.entity.Evaluation;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.EvaluationRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Override
    public void post(User user, Product product) {
        Evaluation evaluation = Evaluation.builder()
                .user(user)
                .product(product)
                .build();

        evaluationRepository.save(evaluation);
    }

    @Override
    public ResponseEntity<HttpStatus> patch(Integer id, Integer evaluationScore, Authentication authentication) throws NoPermissionException{
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(id);

        if (optionalEvaluation.isEmpty())
            throw new NoSuchElementException();

        Evaluation evaluation = optionalEvaluation.get();

        if (evaluation.getUser() != optionalUser.get())
            throw new NoPermissionException();

        if ((evaluationScore < -2) || (evaluationScore > 2))
            throw new IndexOutOfBoundsException();

        evaluation.setEvaluate(evaluationScore);
        evaluationRepository.save(evaluation);

        setOwnerScore(evaluation, evaluationScore);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void setOwnerScore(Evaluation evaluation, Integer evaluationScore) {
        User owner = evaluation.getProduct().getUser();

        owner.setStarScore(Math.max(owner.getStarScore() + 1000 * evaluationScore, 0));
        userRepository.save(owner);
    }
}
