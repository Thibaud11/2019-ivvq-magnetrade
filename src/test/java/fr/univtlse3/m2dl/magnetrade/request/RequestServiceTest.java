package fr.univtlse3.m2dl.magnetrade.request;

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
public class RequestServiceTest {

    private RequestService requestService;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private Request request;

    @Before
    public void setUp() {
        requestService = new RequestService();
        request = new Request(0L, true, "pic", "content", new Date(), new ArrayList<>(), new ArrayList<>());
        requestService.setRepository(requestRepository);
    }

    @Test
    public void testCrudRepository() {
        // le Repository associé à un requestService est de type CrudRepository
        assertThat(requestService.getRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void testCreateOrUpdateRequest() {
        // when: la méthode createOrUpdateRequest est invoquée
        requestService.createOrUpdateRequest(request);
        // then: la méthode save du requestRepository associé est invoquée
        verify(requestService.getRepository()).save(request);
    }

    @Test
    public void testFindById() {
        // given: un requestService
        // when: la méthode findById est invoquée
        requestService.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(requestService.getRepository()).findById(0L);
    }

    @Test
    public void testFindAll() {
        // given: un requestService
        // when: la méthode findAll est invoquée
        requestService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(requestService.getRepository()).findAll();
    }

    @Test
    public void testFindAllActive() {
        // given: un requestService
        // when: la méthode findAllActive est invoquée
        requestService.findAllActive();
        // then: la méthode findAllByIsActiveTrue du Repository associé est invoquée
        verify(requestService.getRepository()).findAllByIsActiveTrue();
    }

    @Test
    public void testDeleteRequest() {
        // given: un requestService
        // when: la méthode deleteRequest est invoquée
        requestService.deleteRequest(0L);
        // then: la méthode deleteById Repository associé est invoquée
        verify(requestService.getRepository()).deleteById(0L);
    }

    @Test
    public void setRequestRepositoryTest() {
        requestService.setRepository(null);
        assertThat(requestService.getRepository(), nullValue());
    }
}