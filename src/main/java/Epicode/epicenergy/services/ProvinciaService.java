package Epicode.epicenergy.services;

import Epicode.epicenergy.RecordsDTO.NewProvinciaDTO;
import Epicode.epicenergy.RecordsDTO.NewProvinciaRespDTO;
import Epicode.epicenergy.entities.Provincia;
import Epicode.epicenergy.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public NewProvinciaRespDTO createProvincia(NewProvinciaDTO dto) {
        Provincia provincia = new Provincia();
        provincia.setSigla(dto.sigla());
        provincia.setProvincia(dto.provincia());
        provincia.setRegione(dto.regione());

        provinciaRepository.save(provincia);
        return new NewProvinciaRespDTO(provincia.getId(), provincia.getSigla(), provincia.getProvincia(), provincia.getRegione());
    }

    public List<NewProvinciaRespDTO> getAllProvince() {
        return provinciaRepository.findAll().stream()
                .map(p -> new NewProvinciaRespDTO(p.getId(), p.getSigla(), p.getProvincia(), p.getRegione()))
                .collect(Collectors.toList());
    }

    public NewProvinciaRespDTO getProvinciaById(UUID id) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Provincia non trovata"));
        return new NewProvinciaRespDTO(provincia.getId(), provincia.getSigla(), provincia.getProvincia(), provincia.getRegione());
    }

    public NewProvinciaRespDTO updateProvincia(UUID id, NewProvinciaDTO dto) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Provincia non trovata"));
        provincia.setSigla(dto.sigla());
        provincia.setProvincia(dto.provincia());
        provincia.setRegione(dto.regione());
        provinciaRepository.save(provincia);
        return new NewProvinciaRespDTO(provincia.getId(), provincia.getSigla(), provincia.getProvincia(), provincia.getRegione());
    }

    public void deleteProvincia(UUID id) {
        provinciaRepository.deleteById(id);
    }
}
