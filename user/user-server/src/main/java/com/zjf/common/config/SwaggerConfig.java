package com.zjf.common.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger配置
 * @author Harry
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger2.enable}")
    private boolean swagger2Enable;
    /**
     *  定义分隔符
     */
    private static final String SPLITOR = ";";

    /**
     * 定义api
     */

    @Bean
    public Docket groupA() {

        return new Docket(DocumentationType.SWAGGER_2)
                        .enable(swagger2Enable)
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(basePackage("com.zjf.modules.*.controller.manager"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统用户中心系统api文档")
                .description("系统用户中心系统api文档")
                .version("1.0")
//                .termsOfServiceUrl("/user")
                .contact(new Contact("harry", "https://www.harry.com",null))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    /**
     * 自定义包匹配规则支持;号及单*号通配符
     * @param basePackage
     * @return
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                String[] startAndEndStr = strPackage.split("\\.\\*\\.");
                if(startAndEndStr.length > 0){
                    boolean isMatch = false;
                    if(startAndEndStr.length > 1){
                        isMatch = input.getPackage().getName().startsWith(startAndEndStr[0]) && input.getPackage().getName().endsWith(startAndEndStr[1]);
                    }else{
                        isMatch = input.getPackage().getName().startsWith(startAndEndStr[0]);
                    }
                    if (isMatch) {
                        return true;
                    }
                }

            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }


}
