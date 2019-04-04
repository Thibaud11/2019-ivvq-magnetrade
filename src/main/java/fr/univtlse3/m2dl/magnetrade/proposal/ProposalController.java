package fr.univtlse3.m2dl.magnetrade.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
