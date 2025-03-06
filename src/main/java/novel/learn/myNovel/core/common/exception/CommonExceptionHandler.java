package novel.learn.myNovel.core.common.exception;

import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.constant.ErrorCodeEnum;
import novel.learn.myNovel.core.common.resp.RestResp;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 通用异常处理器
 * 处理系统异常、数据校验异常以及自定义的业务异常（同包下的BusinessException）
 *
 * */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    /**
     * 处理数据校验异常
     * */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handlerBindException(BindException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAM_ERROR);
    }

    /**
     * 处理自定义业务异常
     * */
    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handlerBusinessException(BusinessException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * 处理系统异常
     * */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handlerException(Exception e){
        log.error(e.getMessage(),e);
        return RestResp.error();
    }
}
