package Gwp.KNUMarket.domain.alarm.presentation;

import Gwp.KNUMarket.domain.alarm.application.AlarmService;
import Gwp.KNUMarket.domain.alarm.data.dto.res.AlarmGetRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alarm")
@Tag(name = "알림")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping
    @Operation(summary = "내 알림 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<List<AlarmGetRes>> get(@Parameter(hidden = true) Authentication authentication) {
        return alarmService.get(authentication);
    }
}
