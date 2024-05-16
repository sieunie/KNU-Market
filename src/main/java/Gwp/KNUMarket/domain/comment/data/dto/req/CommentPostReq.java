package Gwp.KNUMarket.domain.comment.data.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentPostReq {
    @NotNull
    private Integer productId;
    @NotNull
    private Boolean isSecret;
    private String content;
}
