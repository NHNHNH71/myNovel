package novel.learn.myNovel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dao.entity.AuthorInfo;
import novel.learn.myNovel.dao.mapper.AuthorInfoMapper;
import novel.learn.myNovel.dto.AuthorInfoDto;
import novel.learn.myNovel.dto.req.AuthorRegisterReqDto;
import novel.learn.myNovel.manager.cache.AuthorInfoCacheManager;
import novel.learn.myNovel.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 作家模板的业务类
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImp implements AuthorService {

    private final AuthorInfoCacheManager authorInfoCacheManager;

    private final AuthorInfoMapper authorInfoMapper;

    @Override
    public RestResp<Void> register(AuthorRegisterReqDto dto) {
        // 校验该用户是否已注册为作家
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(dto.getUserId());
        if (Objects.nonNull(author)) {
            // 该用户已经是作家，直接返回
            return RestResp.ok();
        }
        // 保存作家注册信息
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setUserId(dto.getUserId());
        authorInfo.setChatAccount(dto.getChatAccount());
        authorInfo.setEmail(dto.getEmail());
        authorInfo.setInviteCode("0");
        authorInfo.setTelPhone(dto.getTelPhone());
        authorInfo.setPenName(dto.getPenName());
        authorInfo.setWorkDirection(dto.getWorkDirection());
        authorInfo.setCreateTime(LocalDateTime.now());
        authorInfo.setUpdateTime(LocalDateTime.now());
        authorInfoMapper.insert(authorInfo);
        // 清除作家缓存
        authorInfoCacheManager.evictAuthorCache();
        return RestResp.ok();
    }

    @Override
    public RestResp<Integer> getStatus(Long userId) {
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(userId);
        return Objects.isNull(author) ? RestResp.ok(null) : RestResp.ok(author.getStatus());
    }
}
