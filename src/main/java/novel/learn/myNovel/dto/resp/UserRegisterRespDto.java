package novel.learn.myNovel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 用户注册的响应dto
 * */
@Data
@Builder
public class UserRegisterRespDto {
    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "用户token")
    private String token;
}
