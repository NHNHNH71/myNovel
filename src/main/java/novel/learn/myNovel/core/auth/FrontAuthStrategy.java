package novel.learn.myNovel.core.auth;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.common.exception.BusinessException;
import novel.learn.myNovel.core.util.JwtUtils;
import novel.learn.myNovel.manager.cache.UserInfoCacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class FrontAuthStrategy implements AuthStrategy{
    private final JwtUtils jwtUtils;

    private final UserInfoCacheManager userInfoCacheManager;

    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // 统一账号认证
        authSSO(jwtUtils, userInfoCacheManager, token);
    }
}
