package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.config.MinioProperties;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class MinioBl {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;

    public void ensureBucket(){
        try{
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build()
            );
            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build()
                );
            }
        } catch (Exception e){
            throw new RuntimeException("Error verificando/creando bucket: " + e.getMessage(), e);
        }
    }

    public String upload(MultipartFile file) {
        return upload(file, "general");
    }

    public String upload(MultipartFile file, String folder) {
        try {
            ensureBucket();

            String safeFolder = (folder == null || folder.isBlank()) ? "general" : folder;

            String original = (file.getOriginalFilename() == null) ? "archivo" : file.getOriginalFilename();
            original = original.replace("..", "").replace("\\", "/"); // básico

            String objectName = safeFolder + "/" + System.currentTimeMillis() + "_" + original;

            try (InputStream in = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(minioProperties.getBucket())
                                .object(objectName)
                                .contentType(file.getContentType())
                                .stream(in, file.getSize(), -1)
                                .build()
                );
            }
            return objectName;

        } catch (Exception e) {
            throw new RuntimeException("Error subiendo archivo a MinIO: " + e.getMessage(), e);
        }
    }

    public GetObjectResponse download(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("archivos")
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error descargando archivo: " + e.getMessage(), e);
        }
    }
    public void delete(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("archivos")
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error eliminando archivo: " + e.getMessage(), e);
        }
    }
    public String presignedGetUrl(String objectName, int minutes) {
        try {
            ensureBucket();

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .expiry(minutes, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error generando URL firmada MinIO: " + e.getMessage(), e);
        }
    }
}
