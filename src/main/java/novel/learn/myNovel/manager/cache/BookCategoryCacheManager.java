package novel.learn.myNovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.BookCategory;
import novel.learn.myNovel.dao.mapper.BookCategoryMapper;
import novel.learn.myNovel.dto.resp.BookCategoryRespDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 小说分类的缓存管理
 * */
@Component
@RequiredArgsConstructor
public class BookCategoryCacheManager {
    private final BookCategoryMapper bookCategoryMapper;

    /**
     * 根据作品方向查询小说分类列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CATEGORY_LIST_CACHE_NAME)
    public List<BookCategoryRespDto> listCategory(Integer workDirection) {
        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCategoryTable.COLUMN_WORK_DIRECTION, workDirection);
        return bookCategoryMapper.selectList(queryWrapper).stream().map(v ->
                BookCategoryRespDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .build()).toList();
    }
}
