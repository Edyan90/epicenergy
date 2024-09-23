package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.exceptions.NotFoundEx;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findWithFilters(Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteNome, Pageable pageable) {

        return clienteRepository.findWithFilters(fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto, parteNome, pageable);
    }

    public Cliente salva(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente trovaPerId(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundEx(clienteId));
    }


    public void cancella(Long clienteId) {
        Cliente cliente = trovaPerId(clienteId);
        clienteRepository.delete(cliente);
    }

    public Cliente mappaDTOaCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(dto.ragioneSociale());
        cliente.setPartitaIva(dto.partitaIva());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setPec(dto.pec());
        cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
        cliente.setDataInserimento(dto.dataInserimento());
        cliente.setDataUltimoContatto(dto.dataUltimoContatto());
        cliente.setNomeContatto(dto.nomeContatto());
        cliente.setCognomeContatto(dto.cognomeContatto());
        cliente.setTelefonoContatto(dto.telefonoContatto());

        return cliente;
    }

    public void aggiornaClienteDaDTO(Cliente cliente, ClienteDTO dto) {
        cliente.setRagioneSociale(dto.ragioneSociale());
        cliente.setPartitaIva(dto.partitaIva());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setPec(dto.pec());
        cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
        cliente.setDataInserimento(dto.dataInserimento());
        cliente.setDataUltimoContatto(dto.dataUltimoContatto());
        cliente.setNomeContatto(dto.nomeContatto());
        cliente.setCognomeContatto(dto.cognomeContatto());
        cliente.setTelefonoContatto(dto.telefonoContatto());

    }
}
