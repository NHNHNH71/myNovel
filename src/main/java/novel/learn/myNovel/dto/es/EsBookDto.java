package novel.learn.myNovel.dto.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import novel.learn.myNovel.dao.entity.BookInfo;

import java.time.ZoneOffset;

/**
 * Elasticsearch存储小说的dto
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EsBookDto {
    /**
     * id
     */
    private Long id;

    /**
     * 作品方向;0-男频 1-女频
     */
    private Integer workDirection;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 类别名
     */
    private String categoryName;

    /**
     * 小说名
     */
    private String bookName;

    /**
     * 作家id
     */
    private Long authorId;

    /**
     * 作家名
     */
    private String authorName;

    /**
     * 书籍描述
     */
    private String bookDesc;

    /**
     * 评分;总分:10 ，真实评分 = score/10
     */
    private Integer score;

    /**
     * 书籍状态;0-连载中 1-已完结
     */
    private Integer bookStatus;

    /**
     * 点击量
     */
    private Long visitCount;

    /**
     * 总字数
     */
    private Integer wordCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 最新章节ID
     */
    private Long lastChapterId;

    /**
     * 最新章节名
     */
    private String lastChapterName;

    /**
     * 最新章节更新时间
     */
    private Long lastChapterUpdateTime;

    /**
     * 是否收费;1-收费 0-免费
     */
    private Integer isVip;

    public static EsBookDto build(BookInfo bookInfo){
        return EsBookDto.builder()
                .id(bookInfo.getId())
                .categoryId(bookInfo.getCategoryId())
                .categoryName(bookInfo.getCategoryName())
                .bookDesc(bookInfo.getBookDesc())
                .bookName(bookInfo.getBookName())
                .authorId(bookInfo.getAuthorId())
                .authorName(bookInfo.getAuthorName())
                .bookStatus(bookInfo.getBookStatus())
                .commentCount(bookInfo.getCommentCount())
                .isVip(bookInfo.getIsVip())
                .score(bookInfo.getScore())
                .visitCount(bookInfo.getVisitCount())
                .wordCount(bookInfo.getWordCount())
                .workDirection(bookInfo.getWorkDirection())
                .lastChapterId(bookInfo.getLastChapterId())
                .lastChapterName(bookInfo.getLastChapterName())
                .lastChapterUpdateTime(bookInfo.getLastChapterUpdateTime()
                        .toInstant(ZoneOffset.ofHours(8)).toEpochMilli())
                .build();
    }
}
