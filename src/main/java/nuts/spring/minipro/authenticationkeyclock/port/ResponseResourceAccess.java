package nuts.spring.minipro.authenticationkeyclock.port;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ResponseResourceAccess {

    @JsonProperty("account")
    private Account account;

    @Data
    static class Account {

        private List<String> roles;
    }
}
