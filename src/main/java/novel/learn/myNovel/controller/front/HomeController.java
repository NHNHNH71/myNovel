package novel.learn.myNovel.controller.front;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.resp.HomeBookRespDto;
import novel.learn.myNovel.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import novel.learn.myNovel.core.constant.ApiRouterConsts;

import java.util.List;

@Tag(name = "HomeController", description = "前台门户-首页模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final HomeService homeService;
    @GetMapping("books")
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        // 测试虚拟线程处理请求
        log.debug("books处理请求的线程：{}", Thread.currentThread());
        System.out.println("ssss");
        return homeService.listHomeBooks();
    }
}
