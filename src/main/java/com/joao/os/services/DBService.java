package com.joao.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.os.domain.Cliente;
import com.joao.os.domain.OS;
import com.joao.os.domain.Tecnico;
import com.joao.os.domain.enuns.Prioridade;
import com.joao.os.domain.enuns.Status;
import com.joao.os.repositories.ClienteRepository;
import com.joao.os.repositories.OSRepository;
import com.joao.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cesar", "088.153.360-28", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Linus", "641.760.040-88", "(88) 94545-4545");
		Cliente c1 = new Cliente(null, "Betina Campos", "958.708.160-92", "(88) 98888-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
