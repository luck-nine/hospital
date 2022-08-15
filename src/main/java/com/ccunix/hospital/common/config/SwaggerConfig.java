package com.ccunix.hospital.common.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2的接口配置
 */
@Configuration
public class SwaggerConfig {
    /*
        系统基础配置
     */
    @Autowired
    private SystemConfig systemConfig;

    /*
        是否开启swagger
     */
    @Value("${swagger.enabled}")
    private boolean enabled;

    /*
        设置请求的统一前缀
     */
    @Value("${swagger.pathMapping}")
    private String pathMapping;

    /*
        创建API
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(enabled)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(".....")
                // 扫描所有.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                /*
                    设置安全模式，swagger可以设置访问token
                 */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                // 新版swagger3.0配置
                .pathMapping(pathMapping)
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     * @return
     */
    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts(){
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     * @return
     */
    private ApiInfo apiInfo(){
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：互联网医院管理系统_接口文档")
                // 描述
                .description("描述：用于管理集团旗下公司的人员信息，具体包括xxx模块...")
                // 作者信息
                .contact(new Contact(systemConfig.getName(),null,"1506024313@qq.com"))
                // 版本
                .version("版本号：" + systemConfig.getVersion())
                .build();
    }

    /**
     * 生成全局通用参数，支持配置多个响应参数
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                // Authorization 听说不好使  官方文档似乎说是关键字
                .name("Authorizations")
                .description("登录令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    /**
     * 生成通用响应信息
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("4xx").description("请求错误").build());
        return responseList;
    }
}
