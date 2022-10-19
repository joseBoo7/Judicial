package com.judicial.servicio;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.dto.DtoCampo;
import com.judicial.dto.DtoCampoPromedio;
import com.judicial.dto.DtoReporte_Atencion;
import com.judicial.dto.DtoReporte_Modal;
import com.judicial.interfaces.InterfazAtencion;
import com.judicial.interfacesServicio.InterfazAtencionServicio;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazUsuarioServicio;
import com.judicial.modelo.Atencion;
import com.judicial.modelo.Ventanilla;

@Service
public class AtencionServicio implements InterfazAtencionServicio {

	@Autowired
	private InterfazAtencion dato;

	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;
	@Autowired
	private InterfazUsuarioServicio servicioUsuario;

	@Override
	public List<Atencion> listar() {
		return (List<Atencion>) dato.findAll();
	}

	@Override
	public Optional<Atencion> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Atencion u) {
		int respuesta = 0;
		Atencion atencion = dato.save(u);
		if (!atencion.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<Atencion> listarPendientes() {
		return dato.findByState();
	}

	@Override
	public int atencionPorVentanilla(Ventanilla ventanilla) {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return dato.findByDate(año, mes, dia, ventanilla);
	}

	@Override
	public List<Atencion> listarPorSede(int id) {
		return dato.findBySede(servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidad(String f1, String f2, int sede,
			int especialidad) {
		return dato.reportAtencion(f1, f2, servicioSede.listarId(sede).get(),
				servicioEspecialidad.listarId(especialidad).get());
	}

	@Override
	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidadPreferencial(String fecha1, String fecha2,
			int id) {
		return dato.reportAtencionPreferencial(fecha1, fecha2, servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidadTotal(String fecha1, String fecha2, int id) {
		return dato.reportAtencionAll(fecha1, fecha2, servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoCampo> reporteAtencionPorUsuario(String fecha1, String fecha2, int id) {
		return dato.reportAtencionPorUsuario(fecha1, fecha2, servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoCampo> reporteAtencionPorVentanilla(String fecha1, String fecha2, int id) {
		return dato.reportAtencionPorVentanilla(fecha1, fecha2, servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoCampoPromedio> reporteAtencionPromedioUsuario(String fecha1, String fecha2, int id) {
		return dato.reportAtencionPromedioUsuario(fecha1, fecha2, servicioSede.listarId(id).get());
	}

	@Override
	public List<DtoReporte_Modal> reporteAtencionDataModal(String fecha, String ausente, int id, int usuario) {
		return dato.reportDataModal(fecha, ausente, servicioSede.listarId(id).get(), usuario);
	}

}
