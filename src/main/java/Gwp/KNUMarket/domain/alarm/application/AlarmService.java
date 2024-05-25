package Gwp.KNUMarket.domain.alarm.application;

import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.data.enums.AlarmType;

public interface AlarmService {
    void post(Product product, User user, AlarmType type);
}
