package nuts.spring.minipro.authenticationkeyclock.controller;


import lombok.Data;

@Data
public class RequestToken {
    private String id;
    private String password;
}
