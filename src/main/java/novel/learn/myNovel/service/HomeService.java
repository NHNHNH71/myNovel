package novel.learn.myNovel.service;

import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.resp.HomeBookRespDto;
import novel.learn.myNovel.dto.resp.HomeFriendLinkRespDto;

import java.util.List;

public interface HomeService {
    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     */
    RestResp<List<HomeBookRespDto>> listHomeBooks();

    /**
     * 首页友情链接列表查询
     *
     * @return 友情链接列表
     */
    RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks();
}
