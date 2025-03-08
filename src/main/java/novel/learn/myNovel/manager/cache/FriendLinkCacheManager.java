package novel.learn.myNovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.HomeFriendLink;
import novel.learn.myNovel.dao.mapper.HomeFriendLinkMapper;
import novel.learn.myNovel.dto.resp.HomeFriendLinkRespDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接的缓存管理类
 * */
@Component
@RequiredArgsConstructor
public class FriendLinkCacheManager {
    private final HomeFriendLinkMapper friendLinkMapper;

    /**
     * 友情链接列表查询，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.HOME_FRIEND_LINK_CACHE_NAME)
    public List<HomeFriendLinkRespDto> listFriendLinks() {
        // 从友情链接表中查询出友情链接列表
        QueryWrapper<HomeFriendLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        return friendLinkMapper.selectList(queryWrapper).stream().map(v -> {
            HomeFriendLinkRespDto respDto = new HomeFriendLinkRespDto();
            respDto.setLinkName(v.getLinkName());
            respDto.setLinkUrl(v.getLinkUrl());
            return respDto;
        }).toList();
    }
}
