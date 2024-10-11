package nuts.spring.minipro.authenticationkeyclock.usecase.token;

public interface RequestTokenUseCase {
    String getToken(String id, String password);
}
