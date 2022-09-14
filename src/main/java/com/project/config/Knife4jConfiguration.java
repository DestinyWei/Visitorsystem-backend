package com.project.config;

import com.project.common.ErrorCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

/**
 * Knife4j 接口文档配置
 *
 * https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        List<Response> responseList = new ArrayList<>();
        Arrays.stream(ErrorCode.values()).forEach(resultCode -> {
            responseList.add(
                    new ResponseBuilder().code(resultCode.getCode()+"").description(resultCode.getMessage()).build()
            );
        });

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("visitor-system")
                        .description("企业访客管理系统")
                        .termsOfServiceUrl("https://gitlab.com/weihaoming1/visitorsystem")
                        .contact(new Contact("魏浩铭", "https://github.com/DestinyWei", "1342348280@qq.com"))
                        .version("1.0")
                        .build())
                .groupName("1.0版本")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.project.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalResponses(GET, responseList)
                .globalResponses(POST, responseList)
                .globalResponses(PUT, responseList)
                .globalResponses(DELETE, responseList);
    }
}