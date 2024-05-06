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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "사용자 프로필 수정 API", description = "수정할 데이터만 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> patch(@RequestParam(required = false) String name, @RequestPart(required = false) MultipartFile image, @Parameter(hidden = true) Authentication authentication) {
        return userService.patch(name, image, authentication);
    }
}
