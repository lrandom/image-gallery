package com.niit.edu.imagegallery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfiger implements WebMvcConfigurer {
    @Value("${UPLOAD_FOLDER}")
    public String UPLOAD_FOLDER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("uploads/**")
                .addResourceLocations("file:"+UPLOAD_FOLDER);
    }


}
