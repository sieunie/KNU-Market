package Gwp.KNUMarket.domain.comment.presentation;

import Gwp.KNUMarket.domain.comment.application.CommentService;
import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "댓글")
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글 작성 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> post(@RequestBody CommentPostReq commentPostReq, @Parameter(hidden = true) Authentication authentication) {
        return commentService.post(commentPostReq, authentication);
    }
}
