package goorm.humandelivery.taxiDriver.application;

import goorm.humandelivery.taxiDriver.domain.model.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateToken {

    //https://velog.io/@dnrwhddk1/Spring-RestTemplate-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
    //https://minkwon4.tistory.com/178
    //https://adjh54.tistory.com/234
    public static String setToken() {
        String url = "http://localhost:8080/api/v1/taxi-driver/auth-tokens";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디에는 대체 뭘 넣어야할 지 모르겠음... 처음에는 Post Restemplate가 아니라고 생각햇으나 결국 요청하는 건 이쪽인 거 같아서 Post RestTemplate로 하긴 했는데 보낼 게 없음
        HttpEntity<String> request = new HttpEntity<>("{}", headers);

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

            if (body.getMessage() != null) {
                throw new RuntimeException("받은 에러 메세지: " + body.getMessage());
            }
        }

        throw new RuntimeException("토큰 요청 실패: " + response.getStatusCode());
    }
}
