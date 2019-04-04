package fr.univtlse3.m2dl.magnetrade.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository repository;

    /**
     * Method to create or update a proposal in the back-end
     *
     * @param proposal the proposal to create or update
     */
    public Proposal createOrUpdateProposal(Proposal proposal) {
        return repository.save(proposal);
    }

}
