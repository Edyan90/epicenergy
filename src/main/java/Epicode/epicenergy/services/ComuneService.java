package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Comune;
import Epicode.epicenergy.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public List<Comune> findAll() {
        return comuneRepository.findAll();
    }

    public Optional<Comune> findById(Long id) {
        return comuneRepository.findById(id);
    }

    public Comune save(Comune comune) {
        return comuneRepository.save(comune);
    }

    public void deleteById(Long id) {
        comuneRepository.deleteById(id);
    }
}
