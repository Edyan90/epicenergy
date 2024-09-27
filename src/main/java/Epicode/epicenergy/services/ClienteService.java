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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ClienteRepository clienteRepository;


    public Page<Cliente> getAllClients(String nome,
                                       Double fatturatoMin,
                                       Double fatturatoMax,
                                       LocalDate dataInserimentoMin,
                                       LocalDate dataInserimentoMax,
                                       LocalDate dataUltimoContattoMin,
                                       LocalDate dataUltimoContattoMax,
                                       String provinciaSedeLegale,
                                       String sortBy,
                                       String direction,
                                       Pageable pageable) {

        Specification<Cliente> spec = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            System.out.println("Filtrando per nome: " + nome);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("ragioneSociale"), "%" + nome + "%"));
        }

        if (fatturatoMin != null) {
            System.out.println("Filtrando per fatturatoMin: " + fatturatoMin);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("fatturatoAnnuale"), fatturatoMin));
        }

        if (fatturatoMax != null) {
            System.out.println("Filtrando per fatturatoMax: " + fatturatoMax);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("fatturatoAnnuale"), fatturatoMax));
        }

        if (dataInserimentoMin != null) {
            System.out.println("Filtrando per dataInserimentoMin: " + dataInserimentoMin);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dataInserimento"), dataInserimentoMin));
        }

        if (dataInserimentoMax != null) {
            System.out.println("Filtrando per dataInserimentoMax: " + dataInserimentoMax);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("dataInserimento"), dataInserimentoMax));
        }

        if (dataUltimoContattoMin != null) {
            System.out.println("Filtrando per dataUltimoContattoMin: " + dataUltimoContattoMin);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dataUltimoContatto"), dataUltimoContattoMin));
        }

        if (dataUltimoContattoMax != null) {
            System.out.println("Filtrando per dataUltimoContattoMax: " + dataUltimoContattoMax);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("dataUltimoContatto"), dataUltimoContattoMax));
        }

        if (provinciaSedeLegale != null && !provinciaSedeLegale.isEmpty()) {
            System.out.println("Filtrando per provinciaSedeLegale: " + provinciaSedeLegale);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("provinciaSedeLegale"), provinciaSedeLegale));
        }

        Sort.Direction sortDirection = (direction != null && direction.equalsIgnoreCase("desc")) ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortBy != null ? sortBy : "ragioneSociale");

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);


        return clienteRepository.findAll(spec, sortedPageable);
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
