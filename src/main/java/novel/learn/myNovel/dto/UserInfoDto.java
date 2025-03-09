package novel.learn.myNovel.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户信息的dto
 * */
@Data
@Builder
public class UserInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer status;
}
