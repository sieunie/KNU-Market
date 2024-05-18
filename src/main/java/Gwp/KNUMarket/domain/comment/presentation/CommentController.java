package Gwp.KNUMarket.domain.comment.presentation;

import Gwp.KNUMarket.domain.comment.application.CommentService;
import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;
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

    @GetMapping("/{productId}")
    @Operation(summary = "댓글 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<List<CommentGetRes>> get(@PathVariable("productId") Integer productId) {
        return commentService.get(productId);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "댓글 수정 API", description = "댓글 작성자만 수정 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> patch(@PathVariable("id") Integer id, @RequestParam String content, @Parameter(hidden = true) Authentication authentication) throws NoPermissionException {
        return commentService.patch(id, content, authentication);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 작성자만 삭제 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content())
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id, @Parameter(hidden = true) Authentication authentication) throws NoPermissionException {
        return commentService.delete(id, authentication);
    }
}
