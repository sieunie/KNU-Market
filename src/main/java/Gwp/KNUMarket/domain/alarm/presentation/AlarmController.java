package Gwp.KNUMarket.domain.alarm.presentation;

import Gwp.KNUMarket.domain.alarm.application.AlarmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alarm")
@Tag(name = "알림")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

}
