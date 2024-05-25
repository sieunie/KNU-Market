package Gwp.KNUMarket.domain.alarm.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlarmGetRes {
    private Integer productId;
    private Integer type;
    private LocalDateTime createdAt;
}

