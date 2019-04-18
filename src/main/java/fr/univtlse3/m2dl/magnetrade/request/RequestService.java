package fr.univtlse3.m2dl.magnetrade.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    /**
     * Method to create a request in the back-end
     *
     * @param request the request to create
     */
    public Request createOrUpdateRequest(Request request) {
        return repository.save(request);
    }

    /**
     * Method to get a request by its id
     *
     * @param id id of the request to get
     */
    public Optional<Request> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Method to get all requests
     *
     * @return all requests
     */
    public List<Request> findAll() {
        return repository.findAll();
    }

    /**
     * Method to get all active requests
     *
     * @return all active requests
     */
    public List<Request> findAllActive() {
        return repository.findAllByActiveTrue();
    }

    /**
     * Method to delete a request
     *
     * @param id id of the request to delete
     */
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }

    /**
     * Getter for property 'repository'.
     *
     * @return Value for property 'repository'.
     */
    public RequestRepository getRepository() {
        return repository;
    }

    /**
     * Setter for property 'repository'.
     *
     * @param repository Value to set for property 'repository'.
     */
    public void setRepository(RequestRepository repository) {
        this.repository = repository;
    }
}
