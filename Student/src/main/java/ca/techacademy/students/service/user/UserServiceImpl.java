package ca.techacademy.students.service.user;

import ca.techacademy.students.model.DTO.CredentialsDTO;
import ca.techacademy.students.model.Profile;
import ca.techacademy.students.util.enums.Role;
import ca.techacademy.students.util.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final WebClient webClient = WebClient.builder().filter(getExchangeFilterFunction()).build();

    @Override
    public Mono<Profile> addNewStudentUser(Profile profile, Role role) throws UserInternalServerException {
        return webClient.post()
                .uri("localhost:8081/api/v1/users?role="+role)
                .header("Authorization", "Bearer ")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(profile), Profile.class)
                .retrieve()
                .bodyToFlux(Profile.class)
                .last();
                
    }

    @Override
    public Mono<Profile> updateStudentProfile(String userId, Map<String, Object> fields) {
        return webClient.patch()
                .uri("http://localhost:8081/api/v1/users/"+userId)
                .header("Authorization", "Bearer ")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(fields), HashMap.class)
                .retrieve()
                .bodyToFlux(Profile.class)
                .last();
    }

    @Override
    public Mono<Boolean> updateStudentPassword(String userId, CredentialsDTO credentialsDTO) throws UserInternalServerException {
        return webClient.put()
                .uri("http://localhost:8081/api/v1/users/"+userId)
                .header("Authorization", "Bearer ")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(credentialsDTO), CredentialsDTO.class)
                .retrieve()
                .bodyToFlux(Boolean.class)
                .last();
    }

    @Override
    public Mono<Boolean> setStudentInactive(String studentId) {
        return webClient.put()
                .uri("http://localhost:8081/api/v1/users/"+studentId+"/inactivate")
                .header("Authorization", "Bearer ")
                .retrieve()
                .bodyToFlux(Boolean.class)
                .last();
    }

    @Override
    public Mono<Profile> getStudentProfile(String studentId) {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/users/"+studentId)
                .header("Authorization", "Bearer ")
                .retrieve()
                .bodyToFlux(Profile.class)
                .last();
    }

    private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (HttpStatus.CONFLICT.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new DuplicateObjectException(body)));
        }
        if (HttpStatus.UNAUTHORIZED.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new FraudsterUserException(body)));
        }
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new UserInternalServerException(body)));
        }
        if (HttpStatus.BAD_REQUEST.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new UserBadRequestException(body)));
        }
        if (HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new ObjectNotFoundException(body)));
        }
        return Mono.just(response);
    }

    private static ExchangeFilterFunction getExchangeFilterFunction(){
        return ExchangeFilterFunction
                .ofResponseProcessor(UserServiceImpl::exchangeFilterResponseProcessor);
    }
}
