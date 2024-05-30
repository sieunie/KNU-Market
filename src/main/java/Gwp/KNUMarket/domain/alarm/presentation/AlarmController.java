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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
@RequestMapping("/api/alarm")
@Tag(name = "알림")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping
    @Operation(summary = "내 알림 조회 API", description = "0: 댓글 알림, 1: 구매 요청 알림, 2: 구매 수락 알림")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<List<AlarmGetRes>> get(@Parameter(hidden = true) Authentication authentication) {
        return alarmService.get(authentication);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "알림 확인 시 삭제 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id, @Parameter(hidden = true) Authentication authentication) throws NoPermissionException {
        return alarmService.delete(id, authentication);
    }
}
