package Gwp.KNUMarket.domain.alarm.application.impl;

import Gwp.KNUMarket.domain.alarm.application.AlarmService;
import Gwp.KNUMarket.global.data.entity.Alarm;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.data.enums.AlarmType;
import Gwp.KNUMarket.global.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final AlarmRepository alarmRepository;

    @Override
    public void post(Product product, User user, AlarmType type) {
        Alarm alarm = Alarm.builder()
                .product(product)
                .user(user)
                .type(type)
                .build();

        alarmRepository.save(alarm);
    }
}
