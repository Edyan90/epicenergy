package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

