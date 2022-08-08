package com.ccunix.hospital.common.config;

import com.ccunix.hospital.common.config.SystemConfig;
import com.ccunix.hospital.common.utils.Constants;
import com.ccunix.hospital.framework.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置 拦截器
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    /**
     * 静态资源 自定义静态资源映射目录
     * @param registry
     *
     * addResourceHandler:  指的是对外暴露的访问路径
     * addResourceLocations： 指的是内部文件放置的目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 本地文件上传路径
         */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
                .addResourceLocations("file:" + SystemConfig.getProfile() + "/");
        /**
         * swagger配置
         */
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
    /**
     * 自定义拦截规则
     *
     * addInterceptors： 需要一个实现HandlerInterceptor接口的拦截器实例
     * addPathPatterns： 用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求拦截
     * excludePathPatterns: 用于设置不需要拦截的过滤规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //  设置访问源地址
        config.addAllowedOriginPattern("*");
        //  设置访问源请求头
        config.addAllowedHeader("*");
        //  设置访问源请求方法
        config.addAllowedMethod("*");
        //  有效期1800秒
        config.setMaxAge(1800L);
        //  添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        //  返回新的CorsFilter
        return new CorsFilter(source);
    }
}
