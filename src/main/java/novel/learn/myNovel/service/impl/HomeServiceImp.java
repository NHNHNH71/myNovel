package novel.learn.myNovel.service.impl;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.resp.HomeBookRespDto;
import novel.learn.myNovel.dto.resp.HomeFriendLinkRespDto;
import novel.learn.myNovel.manager.cache.HomeBookCacheManager;
import novel.learn.myNovel.manager.cache.FriendLinkCacheManager;
import novel.learn.myNovel.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImp implements HomeService {
    private final HomeBookCacheManager homeBookCacheManager;

    private final FriendLinkCacheManager friendLinkCacheManager;

    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }

    @Override
    public RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks() {
        return RestResp.ok(friendLinkCacheManager.listFriendLinks());
    }
}
