package Gwp.KNUMarket.domain.user.presentation;

import Gwp.KNUMarket.domain.user.application.UserService;
import Gwp.KNUMarket.domain.user.data.dto.res.UserGetRes;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "사용자")
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "사용자 프로필 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<UserGetRes> get(@Parameter(hidden = true) Authentication authentication) {
        return userService.get(authentication);
    }
}
