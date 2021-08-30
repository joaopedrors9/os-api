package com.joao.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joao.os.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("SELECT obj FROM Tecnico obj WHERE obj.cpf =:cpf")
	Cliente findByCPF(@Param("cpf") String cpf);
}
