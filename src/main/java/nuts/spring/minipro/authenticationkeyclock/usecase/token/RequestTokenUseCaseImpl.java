package nuts.spring.minipro.authenticationkeyclock.usecase.token;

import lombok.RequiredArgsConstructor;
import nuts.spring.minipro.authenticationkeyclock.port.AuthenticationServerPort;

@RequiredArgsConstructor
public class RequestTokenUseCaseImpl implements RequestTokenUseCase{

    private final AuthenticationServerPort authenticationServerAdapter;


    @Override
    public String getToken(String id, String password) {
        return "";
    }

}
