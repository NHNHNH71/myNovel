package novel.learn.myNovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.BookContent;
import novel.learn.myNovel.dao.mapper.BookContentMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookContentCacheManager {
    private final BookContentMapper bookContentMapper;

    /**
     * 查询小说内容，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> contentQueryWrapper = new QueryWrapper<>();
        contentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID, chapterId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(contentQueryWrapper);
        return bookContent.getContent();
    }

    @CacheEvict(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public void evictBookContentCache(Long chapterId) {
        // 调用此方法自动清除小说内容信息的缓存
    }
}
