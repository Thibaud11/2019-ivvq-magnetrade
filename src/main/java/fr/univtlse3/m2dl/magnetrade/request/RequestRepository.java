package fr.univtlse3.m2dl.magnetrade.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

    List<Request> findAll();

    List<Request> findAllByActiveTrue();

}
