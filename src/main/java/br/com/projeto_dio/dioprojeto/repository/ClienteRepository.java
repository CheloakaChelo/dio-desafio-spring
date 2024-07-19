package br.com.projeto_dio.dioprojeto.repository;

import br.com.projeto_dio.dioprojeto.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
