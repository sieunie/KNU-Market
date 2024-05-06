package Gwp.KNUMarket.domain.user.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserGetProductElementRes {
    private Integer id;
    private String title;
    private String imagePath;
    private Integer price;
    private LocalDateTime createdAt;
}
