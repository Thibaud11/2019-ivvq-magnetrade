package fr.univtlse3.m2dl.magnetrade.proposal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ProposalControllerTest {

    private ProposalController proposalController;

    @MockBean
    private ProposalService proposalService;

    @MockBean
    private Proposal proposal;

    @Before
    public void setUp() {
        proposalController = new ProposalController();
        proposal = new Proposal(0L, true, "pic", "content", new Date(), new ArrayList<>(), new ArrayList<>());
        proposalController.setService(proposalService);
    }

    @Test
    public void testCreateOrUpdateProposal() {
        // when: la méthode createOrUpdateProposal est invoquée
        proposalController.createOrUpdateProposal(proposal);
        // then: la méthode createOrUpdateProposal du proposalService associé est invoquée
        verify(proposalController.getService()).createOrUpdateProposal(proposal);
    }

    @Test
    public void testFindById() {
        try {
            // given: un proposalController
            // when: la méthode findById est invoquée
            proposalController.findById(0L);
            // then: la méthode findById du proposalService associé est invoquée
            verify(proposalController.getService()).findById(0L);
        } catch (ResponseStatusException ignored) {

        }
    }

    @Test
    public void testFindAll() {
        // given: un proposalController
        // when: la méthode findAll est invoquée
        proposalController.findAll();
        // then: la méthode findAll du proposalService associé est invoquée
        verify(proposalController.getService()).findAll();
    }

    @Test
    public void testFindAllActive() {
        // given: un proposalController
        // when: la méthode findAllActive est invoquée
        proposalController.findAllActive();
        // then: la méthode findAllActive du proposalService associé est invoquée
        verify(proposalController.getService()).findAllActive();
    }

    @Test
    public void testDeleteProposal() {
        // given: un proposalController
        // when: la méthode deleteProposal est invoquée
        proposalController.deleteProposal(0L);
        // then: la méthode deleteProposal du proposalService associé est invoquée
        verify(proposalController.getService()).deleteProposal(0L);
    }

    @Test
    public void setProposalServiceTest() {
        proposalController.setService(null);
        assertThat(proposalController.getService(), nullValue());
    }
}