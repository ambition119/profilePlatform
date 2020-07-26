package com.am.profile.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author wpl
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Value("${com.am.profile.api.doc.path:com.am.profile}")
    private String path;

    @Value("${com.am.profile.api.doc.title:Profile Platform API doc}")
    private String title;

    @Value("${com.am.profile.api.doc.version:1.0}")
    private String version;

    @Value("${com.am.profile.api.doc.description:User Profile Platform API doc}")
    private String description;

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(path))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }
}
