package goorm.humandelivery.taxiDriver;

import goorm.humandelivery.taxiDriver.application.ClientService;
import goorm.humandelivery.taxiDriver.application.RestTemplateToken;

public class TaxiDriverCliMain {
    public static void main(String[] args) throws Exception {
        //토큰 정보를ㄹ 불러오고
        String token = RestTemplateToken.setToken();
        System.out.println("토큰 잘 있나? " + token);

        ClientService clientService = new ClientService();
        clientService.connectWithToken(token);

        //이ㅣ제 위치 정보 생각하기....
    }

}
