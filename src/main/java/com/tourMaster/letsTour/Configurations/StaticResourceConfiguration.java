package com.tourMaster.letsTour.Configurations;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Override
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Absolute path to the uploads folder
        String uploadPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        System.out.println("The upload Path is "+uploadPath);

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
    @Override
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
