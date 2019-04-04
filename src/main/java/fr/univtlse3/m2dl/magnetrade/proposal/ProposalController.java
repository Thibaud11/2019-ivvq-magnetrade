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
     * Method to create a proposal in the back-end
     *
     * @param proposal the proposal to create
     */
    @PostMapping("/create")
    public Proposal createProposal(@RequestBody Proposal proposal) {
        return service.createOrUpdateProposal(proposal);
    }

    /**
     * Method to update a proposal in the back-end
     *
     * @param proposal the proposal to update
     */
    @PostMapping("/edit")
    public Proposal editProposal(@RequestBody Proposal proposal) {
        return service.createOrUpdateProposal(proposal);
    }

}
