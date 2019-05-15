package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private FamilyRepository familyRepository;

    @Autowired
    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public FamilyService() {
    }

    public void setFamilyRepository(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Optional<Family> findFamilyById(long l) {
        return familyRepository.findById(l);
    }

    public Family saveFamily(Family family) {
        return familyRepository.save(family);
    }

    public FamilyRepository getFamilyRepository() {
        return familyRepository;
    }

    public List<Family> findAllFamilys() {
        return this.familyRepository.findAll();
    }

    public void deleteFamily(Long id) {
        this.familyRepository.deleteById(id);
    }

}
