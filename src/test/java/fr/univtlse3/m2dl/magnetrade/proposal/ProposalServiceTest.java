package fr.univtlse3.m2dl.magnetrade.proposal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ProposalServiceTest {

    private ProposalService proposalService;

    @MockBean
    private ProposalRepository proposalRepository;

    @MockBean
    private Proposal proposal;

    @Before
    public void setUp() {
        proposalService = new ProposalService();
        proposal = new Proposal(0L, true, "pic", "content", new Date(), new ArrayList<>(), new ArrayList<>());
        proposalService.setRepository(proposalRepository);
    }

    @Test
    public void testCrudRepository() {
        // le Repository associé à un proposalService est de type CrudRepository
        assertThat(proposalService.getRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void testCreateOrUpdateProposal() {
        // when: la méthode createOrUpdateProposal est invoquée
        proposalService.createOrUpdateProposal(proposal);
        // then: la méthode save du proposalRepository associé est invoquée
        verify(proposalService.getRepository()).save(proposal);
    }

    @Test
    public void testFindById() {
        // given: un proposalService
        // when: la méthode findById est invoquée
        proposalService.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(proposalService.getRepository()).findById(0L);
    }

    @Test
    public void testFindAll() {
        // given: un proposalService
        // when: la méthode findAll est invoquée
        proposalService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(proposalService.getRepository()).findAll();
    }

    @Test
    public void testFindAllActive() {
        // given: un proposalService
        // when: la méthode findAllActive est invoquée
        proposalService.findAllActive();
        // then: la méthode findAllByActiveTrue du Repository associé est invoquée
        verify(proposalService.getRepository()).findAllByActiveTrue();
    }

    @Test
    public void testDeleteProposal() {
        // given: un proposalService
        // when: la méthode deleteProposal est invoquée
        proposalService.deleteProposal(0L);
        // then: la méthode deleteById Repository associé est invoquée
        verify(proposalService.getRepository()).deleteById(0L);
    }

    @Test
    public void setProposalRepositoryTest() {
        proposalService.setRepository(null);
        assertThat(proposalService.getRepository(), nullValue());
    }
}