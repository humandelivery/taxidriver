package goorm.humandelivery.taxiDriver;

import goorm.humandelivery.taxiDriver.application.ClientService;
import goorm.humandelivery.taxiDriver.application.RestTemplateToken;

import java.util.Scanner;

public class TaxiDriverCliMain {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("input id: ");
        String loginId = scanner.nextLine();
        System.out.print("password id: ");
        String password = scanner.nextLine();

        String token = RestTemplateToken.RequestLogin(loginId, password);

        System.out.println("토큰 잘 있나? " + token);

        ClientService clientService = new ClientService();
        clientService.connectWithToken(token);

        //이ㅣ제 위치 정보 생각하기....
    }

}
