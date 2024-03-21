package com.sipc.intelligentfarmbackend.utils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MinioUtil {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accesskey}")
    private String accessKey;
    @Value("${minio.secretkey}")
    private String secretKey;
    @Value("${minio.bucketname}")
    private String bucket;
    @Value("${minio.remoteEndpoint}")
    private String remoteEndpoint;

    /**
     * 从is中读取并上传文件,返回文件名称。
     * 如果出现异常会返回null
     *
     * @param multipartFile
     * @return String
     */
    public String uploadFileByFile(MultipartFile multipartFile) {
        //通过UUID形成随机文件名
        String filename = String.valueOf(UUID.randomUUID());
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        String suffix = Objects.requireNonNull(multipartFile.getContentType()).replace('/','.');
        String fullName = filename+suffix;
        log.info("upload:"+fullName);
        try {
            InputStream file = multipartFile.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(fullName).stream(
                                    file, -1, 10485760)
                            .contentType(multipartFile.getContentType())
                            .build());
            return fullName;
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException |
                 ServerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过文件名称获得访问路径（7天）
     * 如果异常将会返回null
     *
     * @param objectName
     * @return {@code String}
     */
    public String downloadFile(String objectName) {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(remoteEndpoint)
                        .credentials(accessKey, secretKey)
                        .build();
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(7, TimeUnit.DAYS)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }

    /**
     * 正常删除返回true。
     *
     * @param objectName
     * @return boolean
     */
    public boolean removeObject(String objectName) {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
        } catch (Exception ignored) {
        }
        return false;
    }

}
