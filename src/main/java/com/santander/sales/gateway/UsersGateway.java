package com.santander.sales.gateway;

import com.santander.sales.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersGateway {
    public Mono<User> getUser(String userId) {
        return WebClient
                .builder()
                .baseUrl(String.format("http://e-commerce-user-app:8001/user/%s", userId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(WebClientResponseException.class, error -> error.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(error));
    }

}
