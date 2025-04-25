package goorm.humandelivery.taxiDriver.application;

import goorm.humandelivery.taxiDriver.domain.model.LoginRequest;
import goorm.humandelivery.taxiDriver.domain.model.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateToken {

    //https://velog.io/@dnrwhddk1/Spring-RestTemplate-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
    //https://minkwon4.tistory.com/178
    //https://adjh54.tistory.com/234
    public static String RequestLogin(String loginId, String password) {
        String url = "http://localhost:8080/api/v1/taxi-driver/auth-tokens";

        //일단 .id password 불러옴
        LoginRequest loginRequest = new LoginRequest(loginId, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        // 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                LoginResponse.class
        );


        LoginResponse body = response.getBody();


        // 응답에서 토큰 꺼내기
        if (response.getStatusCode() == HttpStatus.OK && body != null) {
            if (body.getToken() != null) {
                return body.getToken();
            }

        }

        throw new RuntimeException("토큰 요청 실패: " + response.getStatusCode());
    }
}
