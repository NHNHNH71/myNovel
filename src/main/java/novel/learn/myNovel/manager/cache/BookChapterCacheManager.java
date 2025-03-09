package novel.learn.myNovel.manager.cache;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.dao.entity.BookChapter;
import novel.learn.myNovel.dao.mapper.BookChapterMapper;
import novel.learn.myNovel.dto.resp.BookChapterRespDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
/**
 * 小说章节的缓存管理类
 * */
@Component
@RequiredArgsConstructor
public class BookChapterCacheManager {
    private final BookChapterMapper bookChapterMapper;

    /**
     * 查询小说章节信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public BookChapterRespDto getChapter(Long chapterId) {
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        return BookChapterRespDto.builder()
                .id(chapterId)
                .bookId(bookChapter.getBookId())
                .chapterNum(bookChapter.getChapterNum())
                .chapterName(bookChapter.getChapterName())
                .chapterWordCount(bookChapter.getWordCount())
                .chapterUpdateTime(bookChapter.getUpdateTime())
                .isVip(bookChapter.getIsVip())
                .build();
    }

    @CacheEvict(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public void evictBookChapterCache(Long chapterId) {
        // 调用此方法自动清除小说章节信息的缓存
    }
}
