package novel.learn.myNovel.manager.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.util.ImgVerifyCodeUtils;
import novel.learn.myNovel.core.constant.CacheConsts;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

/**
 * 验证码管理类，用于生成、校验和删除验证码
 * */
@Component
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeManager {
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 生成图形验证码，并放入 Redis 中
     */
    public String genImgVerifyCode(String sessionId) throws IOException {
        String verifyCode = ImgVerifyCodeUtils.getRandomVerifyCode(4);
        String img = ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
        stringRedisTemplate.opsForValue().set(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId,
                verifyCode, Duration.ofMinutes(5));
        return img;
    }

    /**
     * 校验图形验证码
     */
    public boolean imgVerifyCodeOk(String sessionId, String verifyCode) {
        return Objects.equals(stringRedisTemplate.opsForValue()
//                保存验证码时需要一个全局唯一的sessionId来标识这个验证码属于哪一个浏览器会话
//                用户提交注册时会将这个sessionid和验证码一同返回
                .get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId), verifyCode);
    }

    /**
     * 从 Redis 中删除验证码
     */
    public void removeImgVerifyCode(String sessionId) {
        stringRedisTemplate.delete(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId);
    }
}
