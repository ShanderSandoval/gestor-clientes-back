package uce.rh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.rh.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {
}
