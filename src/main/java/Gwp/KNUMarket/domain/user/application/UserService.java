package Gwp.KNUMarket.domain.user.application;

import Gwp.KNUMarket.domain.user.data.dto.res.UserGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ResponseEntity<UserGetRes> get(Authentication authentication);
    ResponseEntity<HttpStatus> patch(String name, MultipartFile image, Authentication authentication);
    ResponseEntity<Boolean> getExist(String name);
}
