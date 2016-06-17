package com.scorpio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scorpio.bean.PictrueResult;
import com.scorpio.service.PictureService;
import com.scorpio.utils.JsonUtils;

@Controller
public class PictureController {
    
    @Autowired
    private PictureService pictureService;
    
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        
         PictrueResult result = pictureService.uploadFastDFSPicture(uploadFile);
         
         return JsonUtils.objectToJson(result);
    }

}
