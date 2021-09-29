package org.mypetclinic.apigateway.client;

import lombok.RequiredArgsConstructor;
import org.mypetclinic.apigateway.dto.Visits;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VisitsServiceClient {

    private final String hostname = "http://visits-service";
    private final WebClient.Builder webClient;

    public Mono<Visits> getVisitsForPets(List<Long> petIds) {
        return webClient.build().get()
                .uri(hostname + "/api/v1/pets/visits?petIds={petIds}", concatToString(petIds))
                .retrieve()
                .bodyToMono(Visits.class);
    }

    private String concatToString(List<Long> petIds) {
        return petIds.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
