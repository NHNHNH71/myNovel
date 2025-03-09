package novel.learn.myNovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.NewsInfo;
import novel.learn.myNovel.dao.mapper.NewsInfoMapper;
import novel.learn.myNovel.dto.resp.NewsInfoRespDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 对新闻进行缓存管理的类
 * */
@Component
@RequiredArgsConstructor
public class NewsCacheManager {
    private final NewsInfoMapper newsInfoMapper;

    /**
     * 最新新闻列表查询，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.LATEST_NEWS_CACHE_NAME)
    public List<NewsInfoRespDto> listLatestNews() {
        // 从新闻信息表中查询出最新发布的两条新闻
        QueryWrapper<NewsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(DatabaseConsts.CommonColumnEnum.CREATE_TIME.getName())
                .last(DatabaseConsts.SqlEnum.LIMIT_2.getSql());
        return newsInfoMapper.selectList(queryWrapper).stream().map(v -> NewsInfoRespDto.builder()
                .id(v.getId())
                .categoryId(v.getCategoryId())
                .categoryName(v.getCategoryName())
                .title(v.getTitle())
                .sourceName(v.getSourceName())
                .updateTime(v.getUpdateTime())
                .build()).toList();
    }

}
