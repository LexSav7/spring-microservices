package org.mypetclinic.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.mypetclinic.apigateway.client.OwnerServiceClient;
import org.mypetclinic.apigateway.client.VisitsServiceClient;
import org.mypetclinic.apigateway.dto.OwnerDetails;
import org.mypetclinic.apigateway.dto.Visits;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    private final OwnerServiceClient ownerServiceClient;
    private final VisitsServiceClient visitsServiceClient;
    private final ReactiveCircuitBreakerFactory cbFactory;

    @GetMapping("/owners/{ownerId}")
    //TODO Type of pet is null, fix
    public Mono<OwnerDetails> getOwnerDetails(final @PathVariable int ownerId) {
        return ownerServiceClient.getOwner(ownerId)
            .flatMap(owner ->
                visitsServiceClient.getVisitsForPets(owner.getPetIds())
                    .transform(it -> {
                        ReactiveCircuitBreaker cb = cbFactory.create("getVisits");
                        return cb.run(it, throwable -> Mono.just(new Visits()));
                    })
                    .map(addVisitsToOwner(owner))
            );
    }

    private Function<Visits, OwnerDetails> addVisitsToOwner(OwnerDetails owner) {
        return visits -> {
            owner.getPets()
                .forEach(pet -> {
                    pet.getVisits().addAll(visits.getItems().stream()
                        .filter(v -> Objects.equals(v.getPetId(), pet.getId()))
                        .collect(Collectors.toList()));
                    System.out.println(pet);
                    pet.getVisits().forEach(System.out::println);
                });
            return owner;
        };
    }
}
