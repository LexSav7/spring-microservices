package org.mypetclinic.visitsservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.mypetclinic.visitsservice.model.Visit;
import org.mypetclinic.visitsservice.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
//TODO изменить в ангуляре маппинги, чтоб здесь было красиво (один общий маппинг на контроллер типа api/v1/visits)
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/owners/*/pets/{petId}/visits")
    public List<Visit> getVisitsForPet(@PathVariable("petId") long petId) {
        return visitService.getVisitsForPet(petId);
    }

    @PostMapping("/owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public Visit addPet(@RequestBody Visit visit, @PathVariable("petId") long petId) {
        return visitService.addVisit(visit, petId);
    }

    @GetMapping("/pets/visits")
    //Using Visits instead of a usual List because it's easier to convert an object to Mono than a collection
    public Visits getVisitsForPets(@RequestParam("petIds") List<Long> petIds) {
        return new Visits(visitService.getVisitsForPets(petIds));
    }

    //TODO read about this annotation once more
    @Value
    static class Visits {
        List<Visit> items;
    }
}
