package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MagnetService {

    private MagnetRepository magnetRepository;

    @Autowired
    public MagnetService(MagnetRepository magnetRepository) {
        this.magnetRepository = magnetRepository;
    }

    public MagnetService() {
    }

    public void setMagnetRepository(MagnetRepository magnetRepository) {
        this.magnetRepository = magnetRepository;
    }

    public Magnet findMagnetById(long l) {
        Optional<Magnet> a = magnetRepository.findById(l);
        return a.orElse(null);
    }

    public void saveMagnet(Magnet magnet) throws IllegalArgumentException {
        if (magnet == null) {
            throw new IllegalArgumentException();
        } else {
            magnetRepository.save(magnet);
        }
    }

    public long countMagnet() {
        return magnetRepository.count();
    }

    public MagnetRepository getMagnetRepository() {
        return magnetRepository;
    }

    public ArrayList<Magnet> findAllMagnets() {
        Iterable<Magnet> temp = this.magnetRepository.findAll();
        ArrayList<Magnet> res = new ArrayList<>();
        temp.forEach(res::add);

        /* Voir si on a besoin de classer les magnet, peut-être par nom */
        // Collections.sort(res, Comparator.comparing(Magnet::getTitre));

        return res;
    }

}
