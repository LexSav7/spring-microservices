package org.mypetclinic.visitsservice.service;

import org.mypetclinic.visitsservice.model.Visit;
import org.mypetclinic.visitsservice.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Visit> getVisitsForPet(long petId) {
        return visitRepository.findAllByPetId(petId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Visit> getVisitsForPets(List<Long> petIds) {
        return visitRepository.findAllByPetIdIn(petIds);
    }

    @Override
    public Visit addVisit(Visit visit, long petId) {
        boolean isVisitIdBlank = (visit.getId() == null || visit.getId() == 0L);
        boolean isBodyPetIdMatchPathPetId = (visit.getPetId() == null) || (visit.getPetId() == petId);

        if (!isVisitIdBlank) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create visit with already provided ID. Remove ID and try again");
        if (!isBodyPetIdMatchPathPetId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet ID in request body does not match Pet ID in URI.");

        visit.setPetId(petId);
        return visitRepository.save(visit);

    }
}
