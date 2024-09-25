package Epicode.epicenergy.RecordsDTO;

import java.util.UUID;

public record NewProvinciaRespDTO(
        UUID id,
        String sigla,
        String provincia,
        String regione
) {
}