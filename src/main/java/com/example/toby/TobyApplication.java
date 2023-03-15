package com.example.toby;

import com.example.controller.HelloController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * Spring Container 란?
 * Business Objects (POJO) + Configuration Metadata 를 조합하여          * Metadata : 속성 데이터
 * 내부의 Bean 이라고 하는 Object 를 구성해서 서버 어플리케이션으로 만들어줌
 */
public class TobyApplication {

    // web server tomcat 구현
    public static void main(String[] args) {
        System.out.println("hello container less standard alone");

        // tomcat 서버 생성
        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        // servlet container 생성
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            // servlet context 정보 초기화 작업
            // HelloController 생성
            HelloController helloController = new HelloController();

            // servlet context 에 servlet 추가
            servletContext.addServlet("helloController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 매핑 : 웹 요청에 들어있는 정보를 활용하여 어떤 로직을 수행하는 코드를 실행할 것인가
                    // 바인딩 : 웹 요청과 응답을 다루는 오브젝트를 사용하지 않음, 웹 요청 정보를 변환하여 사용
                    // - 웹 요청을 가지고 이것을 처리하는 로직 코드에서 사용할 수 있도록 새로운 형태의 타입으로 변환
                    // MVC 바인딩은 더 많은 처리 과정이 존재함

                    // HTTP 3가지 요소
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        String result = helloController.hello(name);

                        // 상태코드는 서블릿 에러가 나지 않는 이상 생략 가능
                        resp.setContentType(MediaType.TEXT_HTML_VALUE);
                        resp.setHeader("Context-Type", "text/plain");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(result);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                    // 매 url 마다 세팅해줘야 함
                    if(req.getRequestURI().equals("/bye") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        String result = helloController.hello(name);

                        // 상태코드는 서블릿 에러가 나지 않는 이상 생략 가능
                        resp.setContentType(MediaType.TEXT_HTML_VALUE);
                        resp.setHeader("Context-Type", "text/plain");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(result);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            })
             .addMapping("/*");
        });
        webServer.start();
    }
}