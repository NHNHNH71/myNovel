package novel.learn.myNovel.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import novel.learn.myNovel.dao.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import novel.learn.myNovel.dto.req.BookSearchReqDto;
import novel.learn.myNovel.dto.resp.BookInfoRespDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 小说信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @date 2025/03/07
 */
public interface BookInfoMapper extends BaseMapper<BookInfo> {
    /**
     * 增加小说点击量
     *
     * @param bookId 小说ID
     */
    void addVisitCount(@Param("bookId") Long bookId);

    /**
     * 小说搜索
     * @param page mybatis-plus 分页对象
     * @param condition 搜索条件
     * @return 返回结果
     * */
    List<BookInfo> searchBooks(IPage<BookInfoRespDto> page, BookSearchReqDto condition);

}
