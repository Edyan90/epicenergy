package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Comune;
import Epicode.epicenergy.exceptions.NotFoundEx;
import Epicode.epicenergy.payloads.ComuneDTO;
import Epicode.epicenergy.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public Page<Comune> findAllPageable(Pageable pageable) {
        return comuneRepository.findAll(pageable);
    }

    public Comune salva(Comune comune) {
        return comuneRepository.save(comune);
    }

    public Comune trovaPerId(UUID comuneId) {
        return comuneRepository.findById(comuneId)
                .orElseThrow(() -> new NotFoundEx(comuneId));
    }

    public void cancella(UUID comuneId) {
        Comune comune = trovaPerId(comuneId);
        comuneRepository.delete(comune);
    }

    public Comune mappaDTOaComune(ComuneDTO dto) {
        Comune comune = new Comune();
        comune.setDenominazione(dto.denominazione());
        comune.setCodiceProvincia(dto.codiceProvincia());
        comune.setProgressivoComune(dto.progressivoComune());

        return comune;
    }

    public void aggiornaComuneDaDTO(Comune comune, ComuneDTO dto) {
        comune.setDenominazione(dto.denominazione());
        comune.setCodiceProvincia(dto.codiceProvincia());
        comune.setProgressivoComune(dto.progressivoComune());

    }
}
