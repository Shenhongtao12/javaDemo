package com.sht.common.service;

import com.sht.common.untils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Aaron
 */
@Service
public class UploadService {

    public RestResponse upload(MultipartFile[] fileArray, String site) {
        try {
            Map<String, Object> result = new HashMap<>(2);
            String url = "";
            //循环上传图片
            for (int i = 0; i < fileArray.length; i++) {
                MultipartFile file = fileArray[i];

                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image == null) {
                    return new RestResponse(400,"上传失败，文件内容不符合");
                }
                long size = file.getSize();
                if (size >= 10 * 1024 * 1024) {
                    return new RestResponse(400,"文件大小不能超过10M");
                }
                //原图
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                fileName = uuid + suffixName;

                File dir = new File(site);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file.transferTo(new File(dir, fileName));

                //缩略图
                String thumbnailName = uuid + "thumbnail" + suffixName;

                String thumbnailUrl = "http://47.98.128.88:8080" + site + "/" + thumbnailName;

                if (size < 200 * 1024) {
                    Thumbnails.of(new String[]{site + "/" + fileName}).scale(1.0D).toFile(site + "/" + thumbnailName);
                } else if (size < 900 * 1024){
                    Thumbnails.of(new String[]{site + "/" + fileName}).scale(1.0D).outputQuality(0.4f).toFile(site + "/" + thumbnailName);
                }else {
                    Thumbnails.of(new String[]{site + "/" + fileName}).scale(1.0D).outputQuality(0.2f).toFile(site + "/" + thumbnailName);
                }
                if (i == 0 ){
                    url += thumbnailUrl;
                }else {
                    url += ","+thumbnailUrl;
                }
            }
            result.put("thumbnailUrl", url);
            return new RestResponse(200, "上传成功", result);
        } catch (Exception e) {
            return new RestResponse(400, e.getMessage());
        }
    }

    /**
     * 删除图片,传入完整的url
     * @param url
     */
    public String deleteImage(String url) {
        String resultInfo = "此图片链接非本平台创建";
        String domain = "47.98.128.88:8080";
        if (url != null && url.contains(domain)){
            //截掉http://eurasia.plus:8080
            String path = url.substring(24);

            String name2 = path.substring(0, path.indexOf("thumbnail"));
            String jpg = url.substring(url.lastIndexOf("."));
            String path2 = name2 + jpg;
            File file = new File(path);
            File file2 = new File(path2);
            if (file.exists()) {
                if (file.delete() && file2.delete()) {
                    resultInfo = "删除成功";
                } else {
                    resultInfo = "删除失败";
                }
            } else {
                resultInfo = "文件不存在";
            }
        }
        return resultInfo;
    }
}
