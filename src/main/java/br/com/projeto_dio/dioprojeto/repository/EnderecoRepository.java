package br.com.projeto_dio.dioprojeto.repository;

import br.com.projeto_dio.dioprojeto.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {

}
