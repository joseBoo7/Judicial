package com.judicial.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judicial.componentes.ComponenteReniec;
import com.judicial.dto.DtoCampo;
import com.judicial.dto.DtoCampoPromedio;
import com.judicial.dto.DtoGrafico_Lineal;
import com.judicial.dto.DtoReporte_Atencion;
import com.judicial.dto.DtoReporte_Modal;
import com.judicial.dto.PersonaReniec;
import com.judicial.dto.Reporte;
import com.judicial.interfacesServicio.InterfazAtencionServicio;
import com.judicial.interfacesServicio.InterfazEspecialidadReservacionServicio;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.modelo.Especialidad;

@RestController
@RequestMapping("/api")
public class ControladorUsuarioRest {

	@Autowired
	private ComponenteReniec reniec;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;
	@Autowired
	private InterfazEspecialidadReservacionServicio servicioEspecialidadReservacion;
	@Autowired
	private InterfazAtencionServicio servicioAtencion;

	@GetMapping("/reniec/{dni}")
	public PersonaReniec listar(@PathVariable("dni") Integer dni) throws Exception {
		PersonaReniec person = new PersonaReniec();
		String[] datos = reniec.checkDniApiReniec(String.valueOf(dni));
		if (datos.length == 0)
			return null;
		else {
			person.setNombres(datos[3]);
			person.setPaterno(datos[1]);
			person.setMaterno(datos[2]);
			person.setUsuario(datos[3].charAt(0) + datos[1]);
			return person;
		}
	}

	@GetMapping("/especialidad/{idSede}")
	public List<Especialidad> listarEspecialidadSede(@PathVariable("idSede") Integer idSede) {
		return servicioEspecialidad.listarPorSede(idSede);
	}

	@GetMapping("/especialidad/cantidad")
	public List<DtoGrafico_Lineal> listarEspecialidadContador() {
		return servicioEspecialidadReservacion.listarEspecialidadesContadas();
	}

	@PostMapping("/reporte")
	public List<DtoReporte_Atencion> listarReporte(@RequestBody Reporte r) {
		if (r.getEspecialidad() > 0) {
			return servicioAtencion.reporteAtencionPorSedeEspecialidad(r.getF1(), r.getF2(), r.getSede(),
					r.getEspecialidad());
		} else {
			if (r.getEspecialidad() == 0) {
				return servicioAtencion.reporteAtencionPorSedeEspecialidadPreferencial(r.getF1(), r.getF2(),
						r.getSede());
			} else {
				return servicioAtencion.reporteAtencionPorSedeEspecialidadTotal(r.getF1(), r.getF2(), r.getSede());
			}
		}

	}
	
	@PostMapping("/reporte/usuario")
	public List<DtoCampo> listarReporteUsuario(@RequestBody Reporte r) {
		return servicioAtencion.reporteAtencionPorUsuario(r.getF1(), r.getF2(), r.getSede());
	}
	
	@PostMapping("/reporte/ventanilla")
	public List<DtoCampo> listarReporteVentanilla(@RequestBody Reporte r) {
		return servicioAtencion.reporteAtencionPorVentanilla(r.getF1(), r.getF2(), r.getSede());
	}
	
	@PostMapping("/reporte/promedio")
	public List<DtoCampoPromedio> listarReportePromedio(@RequestBody Reporte r) {
		return servicioAtencion.reporteAtencionPromedioUsuario(r.getF1(), r.getF2(), r.getSede());
	}

	@PostMapping("/reporte/modal")
	public List<DtoReporte_Modal> listarReporteModal(@RequestBody Reporte r) {
		return servicioAtencion.reporteAtencionDataModal(r.getF1(), r.getF2(), r.getSede(), r.getUsuario());
	}
}
