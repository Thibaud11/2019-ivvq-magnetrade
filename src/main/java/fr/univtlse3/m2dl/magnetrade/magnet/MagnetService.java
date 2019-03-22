package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;

public class MagnetService {

    private MagnetRepository magnetRepository;

    @Autowired
    public MagnetService(MagnetRepository museeRepository) {
        this.magnetRepository = magnetRepository;
    }

}
