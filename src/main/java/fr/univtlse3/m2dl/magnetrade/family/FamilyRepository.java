package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {

    List<Family> findAll();

}
