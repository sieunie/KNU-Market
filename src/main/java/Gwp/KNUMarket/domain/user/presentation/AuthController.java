package Gwp.KNUMarket.domain.user.presentation;

import Gwp.KNUMarket.domain.user.application.AuthService;
import Gwp.KNUMarket.domain.user.data.dto.res.AuthLoginRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    @Operation(summary = "로그인 API", description = "회원이라면 로그인, 회원이 아니라면 회원가입 진행")
    @Parameters({
            @Parameter(name = "code", description = "카카오 로그인 code")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<AuthLoginRes> getLogin(@RequestParam String code) throws IOException {
        return authService.getLogin(code);
    }

    @PatchMapping("/login")
    @Operation(summary = "refreshToken 갱신 API", description = "RefreshToken으로 AccessToken 갱신")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<AuthLoginRes> patchLogin(@Parameter(hidden = true) @RequestHeader("Authorization") String refreshToken) {
        return authService.patchLogin(refreshToken);
    }
}
