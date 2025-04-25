package goorm.humandelivery.taxiDriver.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @JsonProperty("loginId")
    private String email;
    private String password;
}
