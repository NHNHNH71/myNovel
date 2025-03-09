package novel.learn.myNovel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 小说相关内容的响应dto
 * */
@Data
@Builder
public class BookContentAboutRespDto {
    /**
     * 小说信息
     */
    @Schema(description = "小说信息")
    private BookInfoRespDto bookInfo;

    /**
     * 章节信息
     */
    @Schema(description = "章节信息")
    private BookChapterRespDto chapterInfo;

    /**
     * 章节内容
     */
    @Schema(description = "章节内容")
    private String bookContent;
}
