package novel.learn.myNovel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * 图形验证码的响应dto类
 * */
@Data
@Builder
public class ImgVerifyCodeRespDto {
    /**
     * 当前会话ID，用于标识改图形验证码属于哪个会话
     * */
    @Schema(description = "sessionId")
    private String sessionId;

    /**
     * Base64 编码的验证码图片
     * */
    @Schema(description = "Base64 编码的验证码图片")
    private String img;
}
