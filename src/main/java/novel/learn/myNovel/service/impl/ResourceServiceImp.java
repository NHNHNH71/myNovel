package novel.learn.myNovel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import novel.learn.myNovel.core.common.constant.ErrorCodeEnum;
import novel.learn.myNovel.core.common.exception.BusinessException;
import novel.learn.myNovel.core.common.resp.RestResp;
import novel.learn.myNovel.dto.resp.ImgVerifyCodeRespDto;
import novel.learn.myNovel.manager.redis.VerifyCodeManager;
import novel.learn.myNovel.service.ResourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImp implements ResourceService {
    private final VerifyCodeManager verifyCodeManager;

    @Value("${novel.file.upload.path}")
    private String fileUploadPath="D:\\Programing\\idea\\myNovel\\src\\main\\resources\\static\\image";

    @Override
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException {
        String sessionId = IdWorker.get32UUID();
        return RestResp.ok(ImgVerifyCodeRespDto.builder()
                .sessionId(sessionId)
                .img(verifyCodeManager.genImgVerifyCode(sessionId))
                .build());
    }

    @SneakyThrows
    @Override
    public RestResp<String> uploadImage(MultipartFile file) {
        //使用fileUploadPath来指定图片存放的路径
        File targetDir=new File(fileUploadPath);
        //若路径不存在，则新建一个文件夹
        if(!targetDir.exists()){
            boolean isSuccess = targetDir.mkdirs();  // 如果文件夹不存在，创建它
            if (!isSuccess) {
                throw new BusinessException(ErrorCodeEnum.USER_UPLOAD_FILE_ERROR);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        String savePath = now.format(DateTimeFormatter.ofPattern("yyyy")) + File.separator
                + now.format(DateTimeFormatter.ofPattern("MM")) + File.separator
                + now.format(DateTimeFormatter.ofPattern("dd"));
        System.out.println("this is the path: " + savePath);
        String oriName = file.getOriginalFilename();
        assert oriName != null;
        String saveFileName = IdWorker.get32UUID() + oriName.substring(oriName.lastIndexOf("."));
        System.out.println("this is the fileName: " + saveFileName);
        File saveFile = new File(fileUploadPath + savePath, saveFileName);
        System.out.println("this is the saveFile: " + saveFile.getAbsolutePath());
        if (!saveFile.getParentFile().exists()) {
            boolean isSuccess = saveFile.getParentFile().mkdirs();
            if (!isSuccess) {
                throw new BusinessException(ErrorCodeEnum.USER_UPLOAD_FILE_ERROR);
            }
        }
        file.transferTo(saveFile);
        if (Objects.isNull(ImageIO.read(saveFile))) {
            // 上传的文件不是图片
            Files.delete(saveFile.toPath());
            throw new BusinessException(ErrorCodeEnum.USER_UPLOAD_FILE_TYPE_NOT_MATCH);
        }
        return RestResp.ok(savePath + File.separator + saveFileName);
    }
}
