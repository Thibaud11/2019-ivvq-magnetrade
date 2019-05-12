package fr.univtlse3.m2dl.magnetrade.magnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<Magnet> findMagnetById(long l) {
        return magnetRepository.findById(l);
    }

    public Magnet saveMagnet(Magnet magnet) {
        return magnetRepository.save(magnet);
    }

    public MagnetRepository getMagnetRepository() {
        return magnetRepository;
    }

    public List<Magnet> findAllMagnets() {
        return this.magnetRepository.findAll();
    }

    public void deleteMagnet(Long id) {
        this.magnetRepository.deleteById(id);
    }

}
