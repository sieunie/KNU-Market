package Gwp.KNUMarket.domain.evaluation.presentation;

import Gwp.KNUMarket.domain.evaluation.application.EvaluationService;
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

@RestController
@RequiredArgsConstructor
@Tag(name = "평가")
@RequestMapping("/api/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PatchMapping("/{id}")
    @Operation(summary = "거래 평가 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> patch(@PathVariable("id") Integer id, @RequestParam @Parameter(description = "점수는 -2 이상 2 이하 정수로 평가") Integer evaluationScore, @Parameter(hidden = true) Authentication authentication) throws NoPermissionException {
        return evaluationService.patch(id, evaluationScore, authentication);
    }
}
