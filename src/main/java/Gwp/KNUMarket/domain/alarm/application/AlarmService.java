package Gwp.KNUMarket.domain.alarm.application;

import Gwp.KNUMarket.domain.alarm.data.dto.res.AlarmGetRes;
import Gwp.KNUMarket.global.data.entity.Product;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.data.enums.AlarmType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.NoPermissionException;
import java.util.List;


public interface AlarmService {
    void post(Product product, User user, AlarmType type);
    ResponseEntity<List<AlarmGetRes>> get(Authentication authentication);
    ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException;
}
