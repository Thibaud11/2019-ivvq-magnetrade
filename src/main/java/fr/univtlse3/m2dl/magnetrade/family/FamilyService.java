package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Family findFamilyById(long l) {
        Optional<Family> a = familyRepository.findById(l);
        return a.orElse(null);
    }

    public Family saveFamily(Family family) {
        if (family == null) {
            throw new IllegalArgumentException("family can not be null");
        } else {
            return familyRepository.save(family);
        }
    }

    public FamilyRepository getFamilyRepository() {
        return familyRepository;
    }

    public List<Family> findAllFamilys() {
        Iterable<Family> temp = this.familyRepository.findAll();
        List<Family> res = new ArrayList<>();
        temp.forEach(res::add);

        /* Voir si on a besoin de classer les family, peut-être par nom */
        // Collections.sort(res, Comparator.comparing(family::getTitre));

        return res;
    }

    public void deleteFamily(Long id) {
        this.familyRepository.deleteById(id);
    }

}
