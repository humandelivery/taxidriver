package goorm.humandelivery.taxiDriver.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.humandelivery.taxiDriver.domain.model.LocationMessage;
import goorm.humandelivery.taxiDriver.domain.model.LoginRequest;
import goorm.humandelivery.taxiDriver.domain.model.LoginResponse;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {

    private StompSession stompSession;



    public void connectWithToken(String jwtToken) {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders httpHeaders = new WebSocketHttpHeaders();
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("Authorization", "Bearer " + jwtToken);

        String url = "http://localhost:8080/ws";


        stompClient.connectAsync(url, httpHeaders, stompHeaders, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                System.out.println(" WebSocket 성공 id : " + session.getSessionId());

            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                System.out.println("WebSocket 연결 에러: " + exception.getMessage());
            }
        });

        System.out.println("연결 시도 끝남");
    }



}
