package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.sipc.intelligentfarmbackend.pojo.model.res.PictureRes;
import com.sipc.intelligentfarmbackend.service.CommonService;
import com.sipc.intelligentfarmbackend.utils.MinioUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CommonServiceImpl implements CommonService {
    private MinioUtil minioUtil;
    @Override
    public String uploadPicture(MultipartFile multipartFile) {
        return minioUtil.uploadFileByFile(multipartFile);
    }
}
