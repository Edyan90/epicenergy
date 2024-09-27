package Epicode.epicenergy.services;

import Epicode.epicenergy.RecordsDTO.NewIndirizzoDTO;
import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.entities.Indirizzo;
import Epicode.epicenergy.exceptions.BadRequestEx;
import Epicode.epicenergy.exceptions.BadRequestException;
import Epicode.epicenergy.exceptions.NotFoundException;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.repositories.ClienteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ClienteRepository clienteRepository;

//    public Page<Cliente> findWithFilters(Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteNome, Pageable pageable) {
//
//        return clienteRepository.findWithFilters(fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto, parteNome, pageable);
//    }


    public Cliente createClient(ClienteDTO newClienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(newClienteDTO.ragioneSociale());
        cliente.setPartitaIva(newClienteDTO.partitaIva());
        cliente.setEmail(newClienteDTO.email());
        cliente.setIndirizzi(mapToIndirizzi(newClienteDTO.indirizzi()));
        return clienteRepository.save(cliente);
    }

    // public Cliente trovaPerId(UUID clienteId) {
    //     return clienteRepository.findById(clienteId)
    //             .orElseThrow(() -> new NotFoundEx(clienteId));
    // }


    // public void cancella(UUID clienteId) {
    //     Cliente cliente = trovaPerId(clienteId);
    //     clienteRepository.delete(cliente);
    // }

    // public Cliente mappaDTOaCliente(ClienteDTO dto) {
    //     Cliente cliente = new Cliente();
    //     cliente.setRagioneSociale(dto.ragioneSociale());
    //     cliente.setPartitaIva(dto.partitaIva());
    //     cliente.setEmail(dto.email());
    //     cliente.setTelefono(dto.telefono());
    //     cliente.setPec(dto.pec());
    //     cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
    //     cliente.setDataInserimento(dto.dataInserimento());
    //     cliente.setDataUltimoContatto(dto.dataUltimoContatto());
    //     cliente.setNomeContatto(dto.nomeContatto());
    //     cliente.setCognomeContatto(dto.cognomeContatto());
    //     cliente.setTelefonoContatto(dto.telefonoContatto());

    //     return cliente;
    // }

    // public void aggiornaClienteDaDTO(Cliente cliente, ClienteDTO dto) {
    //     cliente.setRagioneSociale(dto.ragioneSociale());
    //     cliente.setPartitaIva(dto.partitaIva());
    //     cliente.setEmail(dto.email());
    //     cliente.setTelefono(dto.telefono());
    //     cliente.setPec(dto.pec());
    //     cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
    //     cliente.setDataInserimento(dto.dataInserimento());
    //     cliente.setDataUltimoContatto(dto.dataUltimoContatto());
    //     cliente.setNomeContatto(dto.nomeContatto());
    //     cliente.setCognomeContatto(dto.cognomeContatto());
    //     cliente.setTelefonoContatto(dto.telefonoContatto());

    // }


    public Cliente updateClient(UUID id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new BadRequestException("Cliente non trovato"));
        cliente.setRagioneSociale(clienteDTO.ragioneSociale());
        cliente.setPartitaIva(clienteDTO.partitaIva());
        cliente.setEmail(clienteDTO.email());
        cliente.setIndirizzi(mapToIndirizzi(clienteDTO.indirizzi()));
        return clienteRepository.save(cliente);
    }

    public void deleteClient(UUID id) {
        clienteRepository.deleteById(id);
    }

    private List<Indirizzo> mapToIndirizzi(List<NewIndirizzoDTO> indirizzoDTOs) {
        return indirizzoDTOs.stream().map(dto -> {
            Indirizzo indirizzo = new Indirizzo();
            indirizzo.setVia(dto.via());
            indirizzo.setCivico(dto.civico());
            indirizzo.setLocalità(dto.località());
            indirizzo.setCap(dto.cap());
            return indirizzo;
        }).collect(Collectors.toList());
    }

    public Cliente getClientById(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + id));
    }


    public Cliente uploadLogoAziendale(UUID clienteId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BadRequestEx("Immagine obbligatoria.");
        }


        Map<String, Object> uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new IOException("Errore durante il caricamento dell'immagine su Cloudinary", e);
        }


        String url = (String) uploadResult.get("url");


        Cliente cliente = trovaPerId(clienteId);
        cliente.setLogoAziendale(url);


        return clienteRepository.save(cliente);
    }

    //
    public List<Cliente> ordinaPerNome() {
        return clienteRepository.ordinaPerNome();
    }

    public List<Cliente> ordinaPerFatturato() {
        return clienteRepository.ordinaPerFatturato();

    }

    public List<Cliente> ordinaPerDataInserimento() {
        return clienteRepository.ordinaPerDataInserimento();
    }

    public List<Cliente> ordinaPerDataUltimoContatto() {
        return clienteRepository.ordinaPerDataUltimoContatto();
    }

    //filtri
    public List<Cliente> filtroFatturatoAnnuale(Double fatturatoMinimo, Double fatturatoMassimo) {
        return clienteRepository.filtroFatturatoAnnuale(fatturatoMinimo, fatturatoMassimo);
    }

    public List<Cliente> filtroDataInserimento(LocalDate primaData, LocalDate secondaData) {
        return clienteRepository.filtroDataInserimento(primaData, secondaData);
    }

    public List<Cliente> filtroDataUltimoContatto(LocalDate primaData, LocalDate secondaData) {
        return clienteRepository.filtroDataUltimoContatto(primaData, secondaData);
    }

    public List<Cliente> filtroNome(String parteDelNome) {
        return clienteRepository.filtroNome(parteDelNome);

    private Cliente trovaPerId(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + clienteId));
    }
}
