package fr.univtlse3.m2dl.magnetrade.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Method to get a proposal by its id
     *
     * @param id id of the proposal to get
     */
    public Optional<Proposal> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Method to get all proposals
     *
     * @return all proposals
     */
    public List<Proposal> findAll() {
        return repository.findAll();
    }

    /**
     * Method to get all active proposals
     *
     * @return all active proposals
     */
    public List<Proposal> findAllActive() {
        return repository.findAllByIsActiveTrue();
    }

    /**
     * Method to delete a proposal
     *
     * @param id id of the proposal to delete
     */
    public void deleteProposal(Long id) {
        repository.deleteById(id);
    }

    /**
     * Getter for property 'repository'.
     *
     * @return Value for property 'repository'.
     */
    public ProposalRepository getRepository() {
        return repository;
    }

    /**
     * Setter for property 'repository'.
     *
     * @param repository Value to set for property 'repository'.
     */
    public void setRepository(ProposalRepository repository) {
        this.repository = repository;
    }
}
