package fr.univtlse3.m2dl.magnetrade.usermagnet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMagnetRepository extends CrudRepository<UserMagnet, Long> {
}
