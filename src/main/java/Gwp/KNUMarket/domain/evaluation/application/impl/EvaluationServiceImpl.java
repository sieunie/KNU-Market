package Gwp.KNUMarket.domain.evaluation.application.impl;

import Gwp.KNUMarket.domain.evaluation.application.EvaluationService;
import Gwp.KNUMarket.global.data.entity.Alarm;
import Gwp.KNUMarket.global.data.entity.Evaluation;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.AlarmRepository;
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

import static Gwp.KNUMarket.global.data.entity.QEvaluation.evaluation;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;

    @Override
    public ResponseEntity<HttpStatus> post(Integer alarmId, Integer evaluationScore, Authentication authentication) throws NoPermissionException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Alarm> optionalAlarm = alarmRepository.findById(alarmId);

        if (optionalAlarm.isEmpty())
            throw new NoSuchElementException();

        Alarm alarm = optionalAlarm.get();

        if (alarm.getUser() != optionalUser.get())
            throw new NoPermissionException();

        if ((evaluationScore < -2) || (evaluationScore > 2))
            throw new IndexOutOfBoundsException();

        Evaluation evaluation = Evaluation.builder()
                .user(optionalUser.get())
                .product(alarm.getProduct())
                .evaluate(evaluationScore)
                .build();
        evaluationRepository.save(evaluation);

        setOwnerScore(alarm.getSender(), evaluationScore);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void setOwnerScore(User owner, Integer evaluationScore) {
        owner.setStarScore(Math.max(owner.getStarScore() + 1000 * evaluationScore, 0));
        userRepository.save(owner);
    }
}
