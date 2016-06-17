package com.scorpio.service;

import org.springframework.web.multipart.MultipartFile;

import com.scorpio.bean.PictrueResult;

public interface PictureService {

    public PictrueResult uploadFtpPicture(MultipartFile uploadFile);
    
    public PictrueResult uploadFastDFSPicture(MultipartFile uploadFile);
    
}
