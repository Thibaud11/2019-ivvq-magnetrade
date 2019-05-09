package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {
}
