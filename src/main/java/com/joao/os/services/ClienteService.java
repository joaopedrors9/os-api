package com.joao.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.os.domain.Cliente;
import com.joao.os.domain.Pessoa;
import com.joao.os.dtos.ClienteDTO;
import com.joao.os.repositories.ClienteRepository;
import com.joao.os.repositories.PessoaRepository;
import com.joao.os.services.exceptions.DataIntegratyViolationException;
import com.joao.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na bade de dados!");
		}
		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return clienteRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException(
					"Cliente possui solicitação de Ordens de Serviço, não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}
 
}
