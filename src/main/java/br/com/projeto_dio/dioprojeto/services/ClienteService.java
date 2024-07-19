package br.com.projeto_dio.dioprojeto.services;

import br.com.projeto_dio.dioprojeto.model.Cliente;
import br.com.projeto_dio.dioprojeto.model.Endereco;
import br.com.projeto_dio.dioprojeto.repository.ClienteRepository;
import br.com.projeto_dio.dioprojeto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    public Iterable<Cliente> listarTodos(){
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.get();
    }

    public void inserir(Cliente cliente){
        salvarClienteComCep(cliente);
    }

    public void atualizar(Long id, Cliente cliente){
        Optional<Cliente> clienteFind = repository.findById(id);

        if(clienteFind.isPresent()){
            salvarClienteComCep(cliente);
        }
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        repository.save(cliente);
    }


}
