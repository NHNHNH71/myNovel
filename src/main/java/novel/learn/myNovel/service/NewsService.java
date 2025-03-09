package novel.learn.myNovel.service;

import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.resp.NewsInfoRespDto;

import java.util.List;

public interface NewsService {
    /**
     * 最新新闻列表查询
     *
     * @return 新闻列表
     */
    RestResp<List<NewsInfoRespDto>> listLatestNews();

    /**
     * 新闻信息查询
     *
     * @param id 新闻ID
     * @return 新闻信息
     */
    RestResp<NewsInfoRespDto> getNews(Long id);
}
