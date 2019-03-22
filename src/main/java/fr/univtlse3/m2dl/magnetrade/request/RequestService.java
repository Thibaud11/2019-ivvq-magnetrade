package fr.univtlse3.m2dl.magnetrade.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    /**
     * Method to create a request in the back-end
     *
     * @param request the request to create
     */
    public Request createRequest(Request request) {
        return repository.save(request);
    }

}
