package com.qiu.controller;

import com.qiu.entity.InfFileDO;
import com.qiu.service.impl.UploadLoadSerivceImpl;
import com.qiu.util.general.CommonResult;
import com.qiu.util.oss.AliyunOssUtil;
import com.qiu.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
@Slf4j
@CrossOrigin
@RestController
public class OssController {
    @Autowired
    private AliyunOssUtil ossUtil;


    @Resource
    UploadLoadSerivceImpl uploadLoadSerivce;

    @RequestMapping("/uploadImage")
    public CommonResult upload(@RequestParam("name") String folderName,
                               @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = folderName+file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                try  {
                    Result<InfFileDO> infFileDOResult = uploadLoadSerivce.uploadFile(file);
                    String url = infFileDOResult.getData().getUrl();
                    return new CommonResult(200, "上传成功", url);
                } catch (Exception e) {
                    log.error("文件上传失败", e);
                    return CommonResult.error("上传失败");
                } finally {

                }
            }
        }
        return CommonResult.error("文件不存在");
    }
}
