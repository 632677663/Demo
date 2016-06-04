package com.scorpio.service.impl;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scorpio.bean.PictrueResult;
import com.scorpio.service.PictureService;
import com.scorpio.utils.FastDFSClient;
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
    
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    
    @Override
    public PictrueResult uploadFtpPicture(MultipartFile uploadFile) {
        
        PictrueResult result = new PictrueResult();
        
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
                result.setError(1);
                result.setMessage("文件上传失败!");
            }else{
                result.setError(0);
            }

        } catch (IOException e) {
            result.setError(1);
            result.setMessage("文件上传失败!");
            e.printStackTrace();
        }
        
        return result;

    }

    @Override
    public PictrueResult uploadFastDFSPicture(MultipartFile uploadFile) {
        //判断图片是否为空
        PictrueResult result = new PictrueResult();
        
        if(uploadFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空上传失败!");
        }
        try {
            FastDFSClient client = new FastDFSClient("classpath:properties/fastdfs.conf");
            
            //获取图片的扩展名
            String originalFileName = uploadFile.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            StringBuffer url = new StringBuffer();
            url.append(IMAGE_SERVER_URL);
            url.append(client.uploadFile(uploadFile.getBytes(), extName));
            //把url响应给客户端
            result.setError(0);
            result.setUrl(url.toString());
        } catch (Exception e) {
            result.setError(1);
            result.setMessage("图片上传失败!");
            e.printStackTrace();
        }
        
        return result;
    }

}
