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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ClienteRepository clienteRepository;


    public Page<Cliente> getAllClients(Specification<Cliente> spec, Pageable pageable) {
        return clienteRepository.findAll(spec, pageable);
    }

    public Cliente createClient(ClienteDTO newClienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(newClienteDTO.ragioneSociale());
        cliente.setPartitaIva(newClienteDTO.partitaIva());
        cliente.setEmail(newClienteDTO.email());
        cliente.setIndirizzi(mapToIndirizzi(newClienteDTO.indirizzi()));
        return clienteRepository.save(cliente);
    }

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


    private Cliente trovaPerId(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + clienteId));
    }
}
