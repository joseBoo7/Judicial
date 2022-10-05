package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazCliente;
import com.judicial.interfacesServicio.InterfazClienteServicio;
import com.judicial.modelo.Cliente;

@Service
public class ClienteServicio implements InterfazClienteServicio {

	@Autowired
	private InterfazCliente dato;

	@Override
	public List<Cliente> listar() {
		return (List<Cliente>) dato.findAll();
	}

	@Override
	public Optional<Cliente> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Cliente u) {
		//int respuesta = 0;
		Cliente cliente = dato.save(u);
		/*if (!cliente.equals(null))
			respuesta = 1;*/
		return cliente.getN_id_cliente();
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public boolean buscarClienteDni(int dni) {
		if (dato.findByDni(String.valueOf(dni)).size() != 0)
			return true;
		else
			return false;
	}

	@Override
	public Cliente obtenerClienteDni(int dni) {
		return dato.findByDni(String.valueOf(dni)).get(0);
	}

	@Override
	public int cantidadPorFecha(int year, int month, int day) {
		return dato.findByDate(year, month, day);
	}

	@Override
	public List<Cliente> listarPendientes() {
		return dato.findByState();
	}

}
