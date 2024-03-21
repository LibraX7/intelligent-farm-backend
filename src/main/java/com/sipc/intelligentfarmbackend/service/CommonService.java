package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PictureRes;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    String uploadPicture(MultipartFile multipartFile);
}
