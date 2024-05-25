package Gwp.KNUMarket.domain.user.data.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserGetRes {
    private Integer id;
    private String name;
    private String imagePath;
    private Integer starScore;
}
