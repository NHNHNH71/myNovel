package novel.learn.myNovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.NewsContent;
import novel.learn.myNovel.dao.entity.NewsInfo;
import novel.learn.myNovel.dao.mapper.NewsContentMapper;
import novel.learn.myNovel.dao.mapper.NewsInfoMapper;
import novel.learn.myNovel.dto.resp.NewsInfoRespDto;
import novel.learn.myNovel.manager.cache.NewsCacheManager;
import novel.learn.myNovel.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {
    private final NewsCacheManager newsCacheManager;

    private final NewsInfoMapper newsInfoMapper;

    private final NewsContentMapper newsContentMapper;

    @Override
    public RestResp<List<NewsInfoRespDto>> listLatestNews() {
        return RestResp.ok(newsCacheManager.listLatestNews());
    }

    @Override
    public RestResp<NewsInfoRespDto> getNews(Long id) {
        NewsInfo newsInfo = newsInfoMapper.selectById(id);
        QueryWrapper<NewsContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.NewsContentTable.COLUMN_NEWS_ID, id)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        NewsContent newsContent = newsContentMapper.selectOne(queryWrapper);
        return RestResp.ok(NewsInfoRespDto.builder()
                .title(newsInfo.getTitle())
                .sourceName(newsInfo.getSourceName())
                .updateTime(newsInfo.getUpdateTime())
                .content(newsContent.getContent())
                .build());
    }
}
