package novel.learn.myNovel.service;

import novel.learn.myNovel.core.common.resp.PageRespDto;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.req.BookSearchReqDto;
import novel.learn.myNovel.dto.resp.BookInfoRespDto;

public interface SearchService {
    /**
     * 小说搜索
     *
     * @param condition 搜索条件
     * @return 搜索结果
     */
    RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition);
}
