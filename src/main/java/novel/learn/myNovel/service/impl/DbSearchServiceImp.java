package novel.learn.myNovel.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.resp.PageRespDto;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dao.entity.BookInfo;
import novel.learn.myNovel.dao.mapper.BookInfoMapper;
import novel.learn.myNovel.dto.req.BookSearchReqDto;
import novel.learn.myNovel.dto.resp.BookInfoRespDto;
import novel.learn.myNovel.service.SearchService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库搜索服务 实现类
 * */
@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "false")
@Service
@RequiredArgsConstructor
@Slf4j
public class DbSearchServiceImp implements SearchService {
    private final BookInfoMapper bookInfoMapper;

    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        Page<BookInfoRespDto> page = new Page<>();
        page.setCurrent(condition.getPageNum());
        page.setSize(condition.getPageSize());
        List<BookInfo> bookInfos = bookInfoMapper.searchBooks(page, condition);
        return RestResp.ok(
                PageRespDto.of(condition.getPageNum(), condition.getPageSize(), page.getTotal(),
                        bookInfos.stream().map(v -> BookInfoRespDto.builder()
                                .id(v.getId())
                                .bookName(v.getBookName())
                                .categoryId(v.getCategoryId())
                                .categoryName(v.getCategoryName())
                                .authorId(v.getAuthorId())
                                .authorName(v.getAuthorName())
                                .wordCount(v.getWordCount())
                                .lastChapterName(v.getLastChapterName())
                                .build()).toList()));
    }
}
