package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/magnet")
public class MagnetController {

    private MagnetService magnetService;

    @Autowired
    public MagnetController(MagnetService magnetService) {
        this.magnetService = magnetService;
    }

    public MagnetController() {
    }

    public void setMagnetService(MagnetService magnetService) {
        this.magnetService = magnetService;
    }

    public MagnetService getMagnetService() {
        return magnetService;
    }

    /**
     * Method to create a magnet in the back-end
     *
     * @param magnet the magnet to create
     */
    @PostMapping({"/create","/update"})
    public Magnet createOrUpdateMagnet(@RequestBody Magnet magnet) {
        return magnetService.saveMagnet(magnet);
    }

    @GetMapping("/{id}")
    public Magnet findMagnetById(@PathVariable("id") long id){
        return magnetService.findMagnetById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Magnet> findAllMagnet(){
        return magnetService.findAllMagnets();
    }

    @DeleteMapping("/delete/{magnetId}")
    public void deleteMagnet(@PathVariable("magnetId") long magnetId){
        magnetService.deleteMagnet(magnetId);
    }

}