package org.mypetclinic.visitsservice.service;

import org.mypetclinic.visitsservice.model.Visit;
import org.mypetclinic.visitsservice.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Visit> getVisitsForPet(long petId) {
        return visitRepository.findAllByPetId(petId);
    }
}
