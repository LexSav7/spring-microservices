package org.mypetclinic.apigateway.client;

import lombok.RequiredArgsConstructor;
import org.mypetclinic.apigateway.dto.OwnerDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OwnerServiceClient {

    private final String hostname = "http://customers-service";
    private final WebClient.Builder webClient;

    public Mono<OwnerDetails> getOwner(long id) {
        return webClient.build().get()
                .uri(hostname + "/owners/{ownerId}", id)
                .retrieve()
                .bodyToMono(OwnerDetails.class);
    }

}
