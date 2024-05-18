package Gwp.KNUMarket.domain.request.presentation;

import Gwp.KNUMarket.domain.request.application.RequestService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "구매 요청")
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/{productId}")
    @Operation(summary = "상품 구매 요청 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> post(@PathVariable("productId") Integer productId, @Parameter(hidden = true) Authentication authentication) {
        return requestService.post(productId, authentication);
    }
}
