package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagnetRepository extends CrudRepository<Magnet, Long> {
}
