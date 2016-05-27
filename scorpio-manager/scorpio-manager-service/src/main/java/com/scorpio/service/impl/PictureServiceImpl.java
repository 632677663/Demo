package com.scorpio.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scorpio.service.PictureService;
import com.scorpio.utils.FtpUtil;
import com.scorpio.utils.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_IP}")
    private String FTP_IP;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Override
    public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
            // 生成新的文件名
            // 取源文件的名字
            String oldName = uploadFile.getOriginalFilename();
            // 生成新的文件名
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));

            boolean flag = FtpUtil.uploadFile(FTP_IP, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    new DateTime().toString("yyyy/MM/dd"), newName, uploadFile.getInputStream());
            
            if(!flag){
                map.put("error", 1);
                map.put("message", "文件上传失败!");
            }else{
                map.put("error", 0);
            }

        } catch (IOException e) {
            map.put("error", 1);
            map.put("message", "文件上传失败!");
            e.printStackTrace();
        }finally {
            return map;
        }

    }

}
