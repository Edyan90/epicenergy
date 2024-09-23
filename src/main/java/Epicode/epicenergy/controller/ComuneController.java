package Epicode.epicenergy.controller;

import Epicode.epicenergy.entities.Comune;
import Epicode.epicenergy.payloads.ComuneDTO;
import Epicode.epicenergy.services.ComuneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping
    public Page<Comune> trovaTuttiComuni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return comuneService.findAllPageable(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comune creaComune(@Valid @RequestBody ComuneDTO comuneDTO) {
        Comune comune = comuneService.mappaDTOaComune(comuneDTO);
        return comuneService.salva(comune);
    }

    @GetMapping("/{comuneId}")
    public Comune trovaComunePerId(@PathVariable Long comuneId) {
        return comuneService.trovaPerId(comuneId);
    }

    @PutMapping("/{comuneId}")
    public Comune aggiornaComune(@PathVariable Long comuneId, @Valid @RequestBody ComuneDTO comuneDTO) {
        Comune comuneEsistente = comuneService.trovaPerId(comuneId);
        comuneService.aggiornaComuneDaDTO(comuneEsistente, comuneDTO);
        return comuneService.salva(comuneEsistente);
    }

    @DeleteMapping("/{comuneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaComune(@PathVariable Long comuneId) {
        comuneService.cancella(comuneId);
    }
}
