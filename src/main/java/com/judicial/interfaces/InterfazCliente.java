package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Cliente;

@Repository
public interface InterfazCliente extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT a FROM Cliente a " + "WHERE a.n_dni_cliente = :dni")
	public List<Cliente> findByDni(@Param(value = "dni") String dni);
	
	@Query(value = "SELECT a FROM Cliente a " + "WHERE a.s_estado_cliente = 'P'")
	public List<Cliente> findByState();

	@Query(value = "SELECT COUNT(a) FROM Cliente a " + "WHERE extract(year from  a.s_fecha_hora_cliente) = :year "
			+ "AND extract(month from  a.s_fecha_hora_cliente) = :month "
			+ "AND extract(day from  a.s_fecha_hora_cliente) = :day "
			+ "AND a.n_dni_cliente is null")
	public int findByDate(@Param(value = "year") int year, @Param(value = "month") int month,
			@Param(value = "day") int day);

}
