package fr.univtlse3.m2dl.magnetrade.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    /**
     * Method to create or update a proposal in the back-end
     *
     * @param proposal the proposal to create or update
     */
    @PostMapping({"/create", "/edit"})
    public Proposal createOrUpdateProposal(@RequestBody Proposal proposal) {
        return service.createOrUpdateProposal(proposal);
    }

    /**
     * Method to get a proposal by its id
     *
     * @param id id of the proposal to get
     */
    @GetMapping("/{id}")
    public Proposal findById(Long id) {
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Method to get all proposals
     *
     * @return all proposals
     */
    @GetMapping("/all")
    public List<Proposal> findAll() {
        return service.findAll();
    }

    /**
     * Method to get all active proposals
     *
     * @return all active proposals
     */
    @GetMapping("/active")
    public List<Proposal> findAllActive() {
        return service.findAllActive();
    }

    /**
     * Method to delete a proposal
     *
     * @param id id of the proposal to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteProposal(@PathVariable Long id) {
        service.deleteProposal(id);
    }

    /**
     * Getter for property 'service'.
     *
     * @return Value for property 'service'.
     */
    public ProposalService getService() {
        return service;
    }

    /**
     * Setter for property 'service'.
     *
     * @param service Value to set for property 'service'.
     */
    public void setService(ProposalService service) {
        this.service = service;
    }
}
