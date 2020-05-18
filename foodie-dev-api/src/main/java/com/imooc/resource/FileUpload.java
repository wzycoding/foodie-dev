package com.imooc.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 上传配置文件属性映射
 */
@Component
@ConfigurationProperties(prefix = "file")
//@PropertySource("classpath:file-upload-dev.properties")
@PropertySource("classpath:file-upload-prod.properties")
public class FileUpload {
    private String imageUserFaceLocation;

    private String imageServerUrl;


    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }

    public String getImageUserFaceLocation() {
        return imageUserFaceLocation;
    }

    public void setImageUserFaceLocation(String imageUserFaceLocation) {
        this.imageUserFaceLocation = imageUserFaceLocation;
    }
}
