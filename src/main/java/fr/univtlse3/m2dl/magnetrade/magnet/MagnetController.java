package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}