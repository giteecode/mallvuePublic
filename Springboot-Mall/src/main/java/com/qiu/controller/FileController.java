package com.qiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiu.dao.InfraFileDao;
import com.qiu.entity.InfFileDO;
import com.qiu.util.file.ServletUtils;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file/")
public class FileController {



    @Resource
    InfraFileDao infraFileDao;



    @GetMapping("/get/{path}")
    @ApiImplicitParam(name = "path", value = "文件附件", required = true, dataTypeClass = MultipartFile.class)
    public void getFile(HttpServletResponse response, @PathVariable("path") String path) throws IOException {

        QueryWrapper<InfFileDO> infFileDOQueryWrapper = new QueryWrapper<>();
        infFileDOQueryWrapper.eq("id",path);
        InfFileDO file = infraFileDao.selectOne(infFileDOQueryWrapper);
        ServletUtils.writeAttachment(response, path, file.getContent());
    }


}
