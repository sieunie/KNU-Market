package Gwp.KNUMarket.domain.request.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestGetRes {
    String userName;
    String userImagePath;
    LocalDateTime createdAt;
}
