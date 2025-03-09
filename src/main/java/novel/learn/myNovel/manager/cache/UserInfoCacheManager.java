package novel.learn.myNovel.manager.cache;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.CacheConsts;
import novel.learn.myNovel.dao.entity.UserInfo;
import novel.learn.myNovel.dto.UserInfoDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import novel.learn.myNovel.dao.mapper.UserInfoMapper;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserInfoCacheManager {
    private final UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.USER_INFO_CACHE_NAME)
    public UserInfoDto getUser(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .status(userInfo.getStatus()).build();
    }
}
