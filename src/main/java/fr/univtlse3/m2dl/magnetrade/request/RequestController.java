package fr.univtlse3.m2dl.magnetrade.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/request")
@Transactional
public class RequestController {

    @Autowired
    private RequestService service;

    /**
     * Method to create or update a request in the back-end
     *
     * @param request the request to create or update
     */
    @PostMapping({"/create", "/edit"})
    public Request createOrUpdateRequest(@RequestBody Request request) {
        return service.createOrUpdateRequest(request);
    }

    /**
     * Method to get a request by its id
     *
     * @param id id of the request to get
     */
    @GetMapping("/{id}")
    public Request findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Method to get all requests
     *
     * @return all requests
     */
    @GetMapping("/all")
    public List<Request> findAll() {
        return service.findAll();
    }

    /**
     * Method to get all active requests
     *
     * @return all active requests
     */
    @GetMapping("/active")
    public List<Request> findAllActive() {
        return service.findAllActive();
    }

    /**
     * Method to delete a request
     *
     * @param id id of the request to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteRequest(@PathVariable Long id) {
        service.deleteRequest(id);
    }

    /**
     * Getter for property 'service'.
     *
     * @return Value for property 'service'.
     */
    public RequestService getService() {
        return service;
    }

    /**
     * Setter for property 'service'.
     *
     * @param service Value to set for property 'service'.
     */
    public void setService(RequestService service) {
        this.service = service;
    }
}
