package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping({"/save","/update"})
    public Magnet createMagnet(@RequestBody Magnet magnet) {
        return magnetService.saveMagnet(magnet);
    }

    @GetMapping("read/{magnetId}")
    public ResponseEntity<Magnet> findMagnetById(@PathVariable("magnetId") long magnetId){
        Magnet m = magnetService.findMagnetById(magnetId);
        if(m == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public List<Magnet> findAllMagnet(){
        return magnetService.findAllMagnets();
    }

    @DeleteMapping("delete/{magnetId}")
    public void deleteMagnet(@PathVariable("magnetId") long magnetId){
        magnetService.deleteMagnet(magnetId);
    }

}