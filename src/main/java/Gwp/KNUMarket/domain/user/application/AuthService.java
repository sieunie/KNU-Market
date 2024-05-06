package Gwp.KNUMarket.domain.user.application;

import Gwp.KNUMarket.domain.user.data.dto.res.AuthLoginRes;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<AuthLoginRes> getLogin(String code) throws IOException;
    ResponseEntity<AuthLoginRes> patchLogin(String refreshToken);
}
