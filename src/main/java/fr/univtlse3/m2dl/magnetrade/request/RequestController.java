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
     * Method to create a request in the back-end
     *
     * @param request the request to create
     */
    @PostMapping("/create")
    public Request createRequest(@RequestBody Request request) {
        return service.createOrUpdateRequest(request);
    }

    /**
     * Method to update a request in the back-end
     *
     * @param request the request to update
     */
    @PostMapping("/edit")
    public Request editRequest(@RequestBody Request request) {
        return service.createOrUpdateRequest(request);
    }

}
