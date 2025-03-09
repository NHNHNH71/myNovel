package novel.learn.myNovel.service;

import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.req.AuthorRegisterReqDto;

public interface AuthorService {
    /**
     * 作家注册
     *
     * @param dto 注册参数
     * @return void
     */
    RestResp<Void> register(AuthorRegisterReqDto dto);

    /**
     * 查询作家状态
     *
     * @param userId 用户ID
     * @return 作家状态
     */
    RestResp<Integer> getStatus(Long userId);
}
