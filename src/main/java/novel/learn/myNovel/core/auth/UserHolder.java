package novel.learn.myNovel.core.auth;

import lombok.experimental.UtilityClass;

/**
 * 在线程开始时，解析token获取用户id
 * 线程通过id去进行后续操作（通过id判断是否有权限进行相关操作）
 * 在线程结束时通过tokenParseInterceptor的afterCompletion清理当前线程的用户数据
 * */

//@UtilityClass让这个类变成一个工具类，无法被实例化
@UtilityClass
public class UserHolder {
    /**
     * 当前线程用户ID
     */
    //ThreadLocal<T>用于声明线程的本地变量
    private static final ThreadLocal<Long> userIdTL = new ThreadLocal<>();

    /**
     * 当前线程作家ID
     */
    private static final ThreadLocal<Long> authorIdTL = new ThreadLocal<>();

    public void setUserId(Long userId) {
        userIdTL.set(userId);
    }

    public Long getUserId() {
        return userIdTL.get();
    }

    public void setAuthorId(Long authorId) {
        authorIdTL.set(authorId);
    }

    public Long getAuthorId() {
        return authorIdTL.get();
    }

    public void clear() {
        userIdTL.remove();
        authorIdTL.remove();
    }
}
