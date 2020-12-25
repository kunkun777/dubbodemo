package com.zxc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @return 关于Api文档的详细信息
     */
    private ApiInfo apiInfo(String title,String description){
        //创建者信息
        Contact contact=new Contact("水苏","https://github.com/kunkun777","3126297263@qq.com");
        return new ApiInfo(title+"-接口文档",
                description,
                "版本1.0",
                "https://github.com/kunkun777",
                contact,
                "mc的压榨",
                "https://github.com/LYZhi.",
                new ArrayList<>()
        );
    }

    @Bean
    public Docket Company_Docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("Company-接口文档","关于公司和公司内部的部门相应的接口，只做了相应的CURD"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/company/**"))
                .build()
                .groupName("Company-V1.0")
                .pathMapping("/");
    }

    @Bean
    public Docket System_Docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("System-接口文档","关于系统和部分用户操作的接口，主要远程调用了Company的接口"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/sys/**"))
                .build()
                .groupName("System-V1.0")
                .pathMapping("/");
    }


}
