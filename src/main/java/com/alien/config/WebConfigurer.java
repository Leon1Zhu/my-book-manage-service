package com.alien.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhuliang on 2017/4/3.
 */
@Configuration
public class WebConfigurer  extends WebMvcConfigurerAdapter {
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;
    @Value("${cors.allow-credentials}")
    private Boolean allowCredentials;
    @Value("${cors.allowed-methods}")
    private String allowedMethods;
    @Value("${cors.max-age}")
    private Long maxAge;
    @Value("${cors.allowed-headers}")
    private String allowedHeaders;
    @Value("${cors.exposed-headers}")
    private String exposedHeaders;



  /*  @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(allowedOrigins != null && !allowedOrigins.isEmpty());
        System.out.println(allowedOrigins );
        if (allowedOrigins != null && !allowedOrigins.isEmpty()) {
            registry.addMapping("/api*//**")
                    .allowedOrigins(allowedOrigins)
                    .allowCredentials(allowCredentials)
                    .allowedMethods("*")
                    .maxAge(maxAge);
        }
    }
*/

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List allowedOriginsList = StringToList(allowedOrigins);
        List allowedMethodList = StringToList(allowedMethods);
        List allowedHeadersList = StringToList(allowedHeaders);
        List exposedHeadersList = StringToList(exposedHeaders);
        corsConfiguration.setAllowedOrigins(allowedOriginsList);
        corsConfiguration.setAllowCredentials(allowCredentials);
        corsConfiguration.setAllowedHeaders(allowedHeadersList);
        corsConfiguration.setAllowedMethods(allowedMethodList);
        corsConfiguration.setExposedHeaders(exposedHeadersList);
        corsConfiguration.setMaxAge(maxAge);
        return corsConfiguration;
    }

    public List StringToList(String content){
        String contentArray[] = content.split(",");
        List contentList = Arrays.asList(contentArray);
        return contentList;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if (allowedOrigins != null && !allowedOrigins.isEmpty()) {
            source.registerCorsConfiguration("/**", buildConfig());
        }
        return new CorsFilter(source);
    }

    @Bean
    public ServletRegistrationBean restServlet(){
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.alienlab.geetest.StartCaptchaServlet");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/gt/register2");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("StartCaptchaServlet1");
        return registrationBean;
    }

}
