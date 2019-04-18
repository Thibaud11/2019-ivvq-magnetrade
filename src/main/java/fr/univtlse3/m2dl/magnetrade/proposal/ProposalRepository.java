package fr.univtlse3.m2dl.magnetrade.proposal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    List<Proposal> findAll();

    List<Proposal> findAllByIsActiveTrue();

}
