package com.example.toby;

import com.example.controller.HelloController;
import com.example.controller.HelloServletController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

/**
 * Spring Container 란?
 * Business Objects (POJO) + Configuration Metadata 를 조합하여          * Metadata : 속성 데이터
 * 내부의 Bean 이라고 하는 Object 를 구성해서 서버 어플리케이션으로 만들어줌
 */
@Configuration
public class TobyDispatcherApplication {

    @Bean
    public HelloServletController helloServletController

    public static void main(String[] args) {
        System.out.println("hello container less standard alone");

        // 스프링 컨테이너 생성
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 필수 - Application Context 초기화
                super.onRefresh();

                // Servlet Container 생성 및 초기화
                ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
                WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
                    // 핸들러
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)).addMapping("/*");
                });

                // tomcat servlet container 실행
                webServer.start();
            }
        };

        // TODO.. Component 등록 테스트
        // 빈 등록
        // 스프링 컨테이너 초기화 작업, Template method 패턴으로 구성
        applicationContext.registerBean(HelloServletController.class);
        applicationContext.refresh();
    }
}