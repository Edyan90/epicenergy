package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.exceptions.BadRequestEx;
import Epicode.epicenergy.exceptions.NotFoundEx;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.repositories.ClienteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

//    public Page<Cliente> findWithFilters(Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteNome, Pageable pageable) {
//
//        return clienteRepository.findWithFilters(fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto, parteNome, pageable);
//    }

    public Cliente salva(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente trovaPerId(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundEx(clienteId));
    }


    public void cancella(UUID clienteId) {
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


    public Cliente uploadLogoAziendale(UUID clienteId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BadRequestEx("Immagine obbligatoria.");
        }

        String url;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new IOException("Errore durante il caricamento dell'immagine", e);
        }

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
    }
}
