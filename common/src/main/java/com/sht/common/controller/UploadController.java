package com.sht.common.controller;

import com.sht.common.service.UploadService;
import com.sht.common.untils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Aaron
 */
@RestController
@RequestMapping({"api/upload"})
public class UploadController extends BaseController{

    @Autowired
    private UploadService uploadService;

    @PostMapping
    public ResponseEntity<RestResponse> uploadImage(@RequestParam("file") MultipartFile[] file, @RequestParam(name = "site", defaultValue = "/common/other") String site) {
        if (file == null) {
            return ResponseEntity.ok(ERROR("文件不能为空"));
        }
        return ResponseEntity.ok(this.uploadService.upload(file, site));
    }

    @DeleteMapping
    public ResponseEntity<RestResponse> delFile(@RequestParam(name = "url") String url) {
        String msg = uploadService.deleteImage(url);
        if ("删除成功".equals(msg)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS(msg));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ERROR(msg));
    }
}
