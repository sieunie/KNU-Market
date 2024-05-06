package Gwp.KNUMarket.domain.user.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthLoginRes {

    private String refreshToken;

    private String accessToken;
}
