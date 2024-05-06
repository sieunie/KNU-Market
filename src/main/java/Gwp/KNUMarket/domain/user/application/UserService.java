package Gwp.KNUMarket.domain.user.application;

import Gwp.KNUMarket.domain.user.data.dto.res.UserGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    ResponseEntity<UserGetRes> get(Authentication authentication);
}
