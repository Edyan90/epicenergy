package Epicode.epicenergy.RecordsDTO;

import java.util.UUID;

public record NewIndirizzoRespDTO(
        UUID id,
        String via,
        String civico,
        String località,
        String cap

) {
}