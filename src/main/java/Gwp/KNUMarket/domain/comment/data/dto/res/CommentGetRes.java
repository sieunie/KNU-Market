package Gwp.KNUMarket.domain.comment.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentGetRes {
    private String userName;
    private String userImagePath;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isSecret;
}
