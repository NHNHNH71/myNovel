package novel.learn.myNovel.core.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import novel.learn.myNovel.core.common.constant.ErrorCodeEnum;

/**
 *
 *自定义业务异常类
 * 业务错误时抛出
 * */
@EqualsAndHashCode(callSuper=true)
@Data
public class BusinessException extends RuntimeException {
    private final ErrorCodeEnum errorCodeEnum;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        super(errorCodeEnum.getMessage(), null, false, false);
        this.errorCodeEnum = errorCodeEnum;
    }
}
