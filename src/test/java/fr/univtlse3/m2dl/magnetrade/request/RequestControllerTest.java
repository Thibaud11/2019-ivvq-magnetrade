package fr.univtlse3.m2dl.magnetrade.request;

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
public class RequestControllerTest {

    private RequestController requestController;

    @MockBean
    private RequestService requestService;

    @MockBean
    private Request request;

    @Before
    public void setUp() {
        requestController = new RequestController();
        request = new Request(0L, true, "pic", "content", new Date(), new ArrayList<>(), new ArrayList<>());
        requestController.setService(requestService);
    }

    @Test
    public void testCreateOrUpdateRequest() {
        // when: la méthode createOrUpdateRequest est invoquée
        requestController.createOrUpdateRequest(request);
        // then: la méthode createOrUpdateRequest du requestService associé est invoquée
        verify(requestController.getService()).createOrUpdateRequest(request);
    }

    @Test
    public void testFindById() {
        try {
            // given: un requestController
            // when: la méthode findById est invoquée
            requestController.findById(0L);
            // then: la méthode findById du requestService associé est invoquée
            verify(requestController.getService()).findById(0L);
        } catch (ResponseStatusException ignored) {

        }
    }

    @Test
    public void testFindAll() {
        // given: un requestController
        // when: la méthode findAll est invoquée
        requestController.findAll();
        // then: la méthode findAll du requestService associé est invoquée
        verify(requestController.getService()).findAll();
    }

    @Test
    public void testFindAllActive() {
        // given: un requestController
        // when: la méthode findAllActive est invoquée
        requestController.findAllActive();
        // then: la méthode findAllActive du requestService associé est invoquée
        verify(requestController.getService()).findAllActive();
    }

    @Test
    public void testDeleteRequest() {
        // given: un requestController
        // when: la méthode deleteRequest est invoquée
        requestController.deleteRequest(0L);
        // then: la méthode deleteRequest du requestService associé est invoquée
        verify(requestController.getService()).deleteRequest(0L);
    }

    @Test
    public void setRequestServiceTest() {
        requestController.setService(null);
        assertThat(requestController.getService(), nullValue());
    }
}