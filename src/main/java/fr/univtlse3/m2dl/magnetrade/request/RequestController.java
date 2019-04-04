package fr.univtlse3.m2dl.magnetrade.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
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

}
