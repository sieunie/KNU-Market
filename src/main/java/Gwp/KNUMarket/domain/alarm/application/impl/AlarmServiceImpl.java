package Gwp.KNUMarket.domain.alarm.application.impl;

import Gwp.KNUMarket.domain.alarm.application.AlarmService;
import Gwp.KNUMarket.domain.alarm.data.dto.res.AlarmGetRes;
import Gwp.KNUMarket.global.data.entity.Alarm;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.data.enums.AlarmType;
import Gwp.KNUMarket.global.repository.AlarmRepository;
import Gwp.KNUMarket.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    @Override
    public void post(Product product, User user, AlarmType type) {
        Alarm alarm = Alarm.builder()
                .product(product)
                .user(user)
                .type(type)
                .build();

        alarmRepository.save(alarm);
    }

    @Override
    public ResponseEntity<List<AlarmGetRes>> get(Authentication authentication) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        List<AlarmGetRes> alarmGetResList = new ArrayList<>();

        for (Alarm alarm : alarmRepository.findByUser(optionalUser.get())) {
            alarmGetResList.add(new AlarmGetRes(
                    alarm.getId(),
                    alarm.getProduct().getId(),
                    alarm.getType().ordinal(),
                    alarm.getCreatedAt()
            ));
        }

        return new ResponseEntity<>(alarmGetResList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(authentication.getName()));

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        Optional<Alarm> optionalAlarm = alarmRepository.findById(id);

        if (optionalAlarm.isEmpty())
            throw new NoSuchElementException();

        if (optionalUser.get() != optionalAlarm.get().getUser())
            throw new NoPermissionException();

        alarmRepository.delete(optionalAlarm.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
