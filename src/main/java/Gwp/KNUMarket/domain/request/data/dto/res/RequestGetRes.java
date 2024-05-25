package Gwp.KNUMarket.domain.request.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestGetRes {
    Integer id;
    String userName;
    String userImagePath;
    LocalDateTime createdAt;
}
