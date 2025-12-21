package com.example.ProyectoFinal.Api;


import com.example.ProyectoFinal.Bl.MinioBl;
import io.minio.GetObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class MinioApi {
    @Autowired
    private MinioBl minioBl;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioBl.upload(file, "/");
        return ResponseEntity.ok(Map.of(
                "message", "Archivo subido",
                "objectName", objectName
        ));
    }

    @GetMapping("/download/{objectName}")
    public ResponseEntity<?> download(@PathVariable String objectName) {
        GetObjectResponse obj = minioBl.download(objectName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + objectName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, obj.headers().get("Content-Type"))
                .body(new InputStreamResource(obj));
    }

    @DeleteMapping("/delete/{objectName}")
    public ResponseEntity<?> delete(@PathVariable String objectName) {
        minioBl.delete(objectName);
        return ResponseEntity.ok(Map.of("message", "Archivo eliminado"));
    }
}
