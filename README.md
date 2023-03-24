# MVC 스프링 라이프 사이클
   - End-User > Request > Filter > Dispatcher Servlet > Handler Mapping > Handler Adapter > Controller > Service > Respository > View > Response
                  ㄴ TCP/IP > web server > was server
      * Filter : encoding, request wrapping, multipartfile
      * Controller 전 Handler Adapter 에서 Mapping Adapter 처리 (어느 컨트롤러로 갈지 지정)
         * Adapter : 컨트롤러로 들어갈 수 있게 변환해주는 역할    ex) 110v 돼지코 변압기

   - Servlet Container(Tomcat) > Sptring Container
      * Servlet Container : 중앙 컨트롤, servlet 3.0
      * Spring Container : mvc 스프링 라이프 사이클
         * Spring Container 란?
         * Business Objects (POJO) + Configuration Metadata 를 조합하여          * Metadata : 속성 데이터
         * 내부의 Bean 이라고 하는 Object 를 구성해서 서버 어플리케이션으로 만들어줌.
            * Bean에는 메소드(@Bean)와 클래스(@Component)가 있다.
            * 컴포넌트 스캔 : 빈을 읽어오는 것
      * Context Switch : Sprint Container 내 Bean 스위칭
         * Bean : MVC(controller, service, repository) 묶음 - 객체

# web server tomcat 구현
   - com.example.toby.TobyApplication.java
   - com.example.toby.TobyDispatcherApplication.java
