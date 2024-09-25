package Epicode.epicenergy.services;

import Epicode.epicenergy.RecordsDTO.NewIndirizzoDTO;
import Epicode.epicenergy.RecordsDTO.NewIndirizzoRespDTO;
import Epicode.epicenergy.entities.Indirizzo;
import Epicode.epicenergy.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public NewIndirizzoRespDTO createIndirizzo(NewIndirizzoDTO dto) {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(dto.via());
        indirizzo.setCivico(dto.civico());
        indirizzo.setLocalità(dto.località());
        indirizzo.setCap(dto.cap());


        indirizzoRepository.save(indirizzo);
        return new NewIndirizzoRespDTO(indirizzo.getId(), indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getLocalità(), indirizzo.getCap());
    }

    public List<NewIndirizzoRespDTO> getAllIndirizzi() {
        return indirizzoRepository.findAll().stream()
                .map(i -> new NewIndirizzoRespDTO(i.getId(), i.getVia(), i.getCivico(), i.getLocalità(), i.getCap()))
                .collect(Collectors.toList());
    }

    public NewIndirizzoRespDTO getIndirizzoById(UUID id) {
        Indirizzo indirizzo = indirizzoRepository.findById(id).orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
        return new NewIndirizzoRespDTO(indirizzo.getId(), indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getLocalità(), indirizzo.getCap());
    }

    public NewIndirizzoRespDTO updateIndirizzo(UUID id, NewIndirizzoDTO dto) {
        Indirizzo indirizzo = indirizzoRepository.findById(id).orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
        indirizzo.setVia(dto.via());
        indirizzo.setCivico(dto.civico());
        indirizzo.setLocalità(dto.località());
        indirizzo.setCap(dto.cap());


        indirizzoRepository.save(indirizzo);
        return new NewIndirizzoRespDTO(indirizzo.getId(), indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getLocalità(), indirizzo.getCap());
    }

    public void deleteIndirizzo(UUID id) {
        indirizzoRepository.deleteById(id);
    }
}
