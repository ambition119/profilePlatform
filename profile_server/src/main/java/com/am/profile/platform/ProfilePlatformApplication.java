package com.am.profile.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = { "com.am.profile.platform.dao" })
public class ProfilePlatformApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ProfilePlatformApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProfilePlatformApplication.class, args);
        LOG.info("ProfilePlatformApplication start run!");
    }
}
