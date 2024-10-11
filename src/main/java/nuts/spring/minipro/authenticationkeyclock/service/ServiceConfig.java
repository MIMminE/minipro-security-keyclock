package nuts.spring.minipro.authenticationkeyclock.service;

import nuts.spring.minipro.authenticationkeyclock.port.AuthenticationServerAdapter;
import nuts.spring.minipro.authenticationkeyclock.port.AuthenticationServerPort;
import nuts.spring.minipro.authenticationkeyclock.usecase.create.CreateResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.create.DefaultCreateResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.delete.DefaultDeleteResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.delete.DeleteResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.get.DefaultGetResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.get.GetResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.token.RequestTokenUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.token.RequestTokenUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    AuthenticationServerPort authenticationServerPort() {
        return new AuthenticationServerAdapter();
    }

    @Bean
    RequestTokenUseCase requestTokenUseCase() {
        return new RequestTokenUseCaseImpl(authenticationServerPort());
    }

    @Bean
    CreateResourceUseCase createResourceUseCase() {
        return new DefaultCreateResourceUseCase();
    }

    @Bean
    DeleteResourceUseCase deleteResourceUseCase() {
        return new DefaultDeleteResourceUseCase();
    }

    @Bean
    GetResourceUseCase getResourceUseCase() {
        return new DefaultGetResourceUseCase();
    }
}