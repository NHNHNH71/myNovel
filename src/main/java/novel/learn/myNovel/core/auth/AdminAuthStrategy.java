package novel.learn.myNovel.core.auth;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.common.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAuthStrategy implements AuthStrategy {
    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // TODO 平台后台 token 校验
    }
}
