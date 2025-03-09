package novel.learn.myNovel.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 作家信息的dto
 * */
@Data
@Builder
public class AuthorInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String penName;

    private Integer status;
}
