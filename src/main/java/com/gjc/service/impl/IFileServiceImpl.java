package com.gjc.service.impl;

import com.gjc.service.IFileService;
import com.gjc.util.FTPUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
@Slf4j
public class IFileServiceImpl implements IFileService {

//    private Logger log = LoggerFactory.getLogger(IFileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件，上传文件的文件名：{}，上传的路径是{}，新文件名{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件上传成功

            // 将targetfile 上传到我们的ftp服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传ftp服务器上

            //上传完之后，删除upload下面的文件
            targetFile.delete();

        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }
}
