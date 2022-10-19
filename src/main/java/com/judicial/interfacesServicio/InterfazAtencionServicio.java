package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.dto.DtoCampo;
import com.judicial.dto.DtoCampoPromedio;
import com.judicial.dto.DtoReporte_Atencion;
import com.judicial.dto.DtoReporte_Modal;
import com.judicial.modelo.Atencion;
import com.judicial.modelo.Ventanilla;

public interface InterfazAtencionServicio {

	public List<Atencion> listar();

	public Optional<Atencion> listarId(int id);

	public int guardar(Atencion u);

	public void eliminar(int id);

	public List<Atencion> listarPendientes();

	public int atencionPorVentanilla(Ventanilla ventanilla);

	public List<Atencion> listarPorSede(int id);

	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidad(String fecha1, String fecha2, int id, int id2);
	
	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidadPreferencial(String fecha1, String fecha2, int id);

	public List<DtoReporte_Atencion> reporteAtencionPorSedeEspecialidadTotal(String fecha1, String fecha2, int id);
	
	public List<DtoCampo> reporteAtencionPorUsuario(String fecha1, String fecha2, int id);
	
	public List<DtoCampo> reporteAtencionPorVentanilla(String fecha1, String fecha2, int id);
	
	public List<DtoCampoPromedio> reporteAtencionPromedioUsuario(String fecha1, String fecha2, int id);
	
	public List<DtoReporte_Modal> reporteAtencionDataModal(String fecha, String ausente, int id, int id2);
}
