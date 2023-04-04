package com.bazzi.cherryfeed.configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
@EnableSwagger2    // Swagger2를 사용하겠다는 어노테이션
@SuppressWarnings("unchecked")    // warning밑줄 제거를 위한 태그
public class SwaggerConfig extends WebMvcConfigurationSupport {

    //리소스 핸들러 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    // API마다 구분짓기 위한 설정.
    @Bean
    public Docket userApi() {
        return getDocket("회원가입/로그인", Predicates.or(
                PathSelectors.regex("/api/v1/users.*")));
    }

    @Bean
    public Docket coupleApi() {
        return getDocket("커플", Predicates.or(
                PathSelectors.regex("/api/v1/connection.*")));
    }


    @Bean
    public Docket calendarApi() {
        return getDocket("캘린더", Predicates.or(
                PathSelectors.regex("/api/v1/calender.*")));
    }


    @Bean
    public Docket commonApi() {
        return getDocket("기념일", Predicates.or(
                PathSelectors.regex("/api/v1/anvsy.*")));

    }

    @Bean
    public Docket postApi() {
        return getDocket("게시물", Predicates.or(
                PathSelectors.regex("/api/v1/post.*")));
    }

    @Bean
    public Docket fileApi() {
        return getDocket("파일업로드다운로드", Predicates.or(
                PathSelectors.regex("/api/v1/file.*")));
    }


    @Bean
    public Docket allApi() {
        return getDocket("전체", Predicates.or(
                PathSelectors.regex("/*.*")));
    }

    //swagger 설정.
    public Docket getDocket(String groupName, Predicate<String> predicate) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bazzi.cherryfeed")) //이부분 설정 중요한듯 luca
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    //swagger ui 설정.
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }
    //상대경로
    //
    //이미지 업로드 문제
    //
    //페이징 처리
    //
    //오류코드 처리
    //
    //로그인
}