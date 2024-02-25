package com.qiu.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiu.dao.InfraFileDao;
import com.qiu.entity.InfFileDO;

import com.qiu.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
public class UploadLoadSerivceImpl {



    @Value ("${server.port}")
    private Integer port;

    @Resource
    InfraFileDao infraFileDao;


    public Result<InfFileDO> uploadFile(MultipartFile file)  {
        String path=file.getOriginalFilename();
        byte[] bytes=null;
        try {
             bytes = IoUtil.readBytes(file.getInputStream());
           }catch(Exception e){
             e.printStackTrace();
           }

        //删除已有path
        QueryWrapper<InfFileDO> infFileDOQueryWrapper = new QueryWrapper<>();
        infFileDOQueryWrapper.eq("id",path);
        infraFileDao.delete(infFileDOQueryWrapper);
        // 保存到数据库
        InfFileDO infFileDO = new InfFileDO();
        infFileDO.setId(path);
        infFileDO.setType(FileTypeUtil.getType(new ByteArrayInputStream(bytes)));
        infFileDO.setContent(bytes);
        String url="http://localhost:"+port+"/file/get/"+path;
        infFileDO.setUrl(url);
        infraFileDao.insert(infFileDO);
        return  Result.ok(infFileDO);
    }

    public Result<InfFileDO> uploadFile(ByteArrayInputStream inputStream,String path) throws IOException {
        byte[] bytes = IoUtil.readBytes(inputStream);
        // 保存到数据库
        InfFileDO infFileDO = new InfFileDO();
        infFileDO.setId(path);
        infFileDO.setType(FileTypeUtil.getType(inputStream));
        infFileDO.setContent(bytes);
        String url="http://localhost:"+port+"/file/get/"+path;
        infFileDO.setUrl(url);
        infraFileDao.insert(infFileDO);
        return  Result.ok(infFileDO);
    }
}
