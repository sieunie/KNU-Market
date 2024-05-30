package Gwp.KNUMarket.domain.alarm.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlarmGetRes {
    private Integer id;
    private Integer productId;
    private String senderName;
    private Integer type;
    private LocalDateTime createdAt;
}

