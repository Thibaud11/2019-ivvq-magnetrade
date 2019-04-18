package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/magnet")
public class MagnetController {

    @Autowired
    private MagnetService magnetService;

    /**
     * Method to create a magnet in the back-end
     *
     * @param magnet the magnet to create
     */
    @PostMapping({"/save","/edit"})
    public Magnet createMagnet(@RequestBody Magnet magnet) {
        return magnetService.saveMagnet(magnet);
    }

    @GetMapping("read/{magnetId}")
    public ResponseEntity<Magnet> getMagnet(@PathVariable("magnetId") long magnetId){
        Magnet m = magnetService.findMagnetById(magnetId);
        if(m == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{userId}")
    public void deleteMagnet(@PathVariable("magnetId") long magnetId){
        magnetService.deleteMagnet(magnetId);
    }

}