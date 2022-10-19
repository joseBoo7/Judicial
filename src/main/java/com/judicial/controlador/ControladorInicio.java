package com.judicial.controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import javax.print.PrintException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.judicial.componentes.ComponenteReniec;
import com.judicial.interfacesServicio.InterfazAtencionServicio;
import com.judicial.interfacesServicio.InterfazClienteServicio;
import com.judicial.interfacesServicio.InterfazEspecialidadReservacionServicio;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazReservacionServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazUsuarioServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.interfacesServicio.InterfazVideoServicio;
import com.judicial.modelo.Atencion;
import com.judicial.modelo.Cliente;
import com.judicial.modelo.Especialidad;
import com.judicial.modelo.EspecialidadReservacion;
import com.judicial.modelo.Reservacion;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Usuario;
import com.judicial.modelo.Video;

@Controller
@RequestMapping(value = "/inicio")
public class ControladorInicio {

	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;
	@Autowired
	private InterfazClienteServicio servicioCliente;
	@Autowired
	private InterfazAtencionServicio servicioAtencion;
	@Autowired
	private InterfazUsuarioServicio servicioUsuario;
	@Autowired
	private InterfazVideoServicio servicioVideo;
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;
	@Autowired
	private InterfazReservacionServicio servicioReservacion;
	@Autowired
	private InterfazEspecialidadReservacionServicio servicioEspecialidadReservacion;
	@Autowired
	private ComponenteReniec reniec;

	@GetMapping("")
	public String listar(Model modelo) throws Exception {
		// String [] datos = reniec.checkDniApiReniec("72529053");
		// System.out.println(reniec.getAgeFromDate("03/08/1997"));
		List<Sede> sede = servicioSede.listar();
		modelo.addAttribute("sedes", sede);
		return "VistaInicio_Sede";
	}

	@GetMapping("/sede/tv")
	public String listarSede(Model modelo) {
		List<Sede> sede = servicioSede.listar();
		modelo.addAttribute("sedes", sede);
		return "VistaInicio_Tv_Sede";
	}

	@GetMapping("/tv/{sede}")
	public String verTv(@PathVariable int sede, Model modelo) {
		List<Video> videos = servicioVideo.listarPorSede(sede);
		modelo.addAttribute("videos", videos);
		return "VistaInicio_Tv";
	}

	public int generarCodigo() {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return servicioCliente.cantidadPorFecha(año, mes, dia);
	}

	@GetMapping("/registrar/{sede}/{idEspecialidad}/{preferencial}/{dni}")
	public String registrarCliente(@PathVariable int sede, @PathVariable int idEspecialidad,
			@PathVariable int preferencial, @PathVariable int dni, Model modelo) throws Exception {
		int idCliente;
		// °Refactorizar datos estaticos
		if (preferencial == 1) {
			Cliente clientePreferencial = new Cliente("A");
			clientePreferencial.setS_codigo_letra_cliente("PRE-");
			clientePreferencial.setSede_n_id_sede(servicioSede.listarId(sede).get());
			clientePreferencial.setN_codigo_cliente(generarCodigo() + 1);
			idCliente = servicioCliente.guardar(clientePreferencial);
		} else {
			if (dni == 0) {
				Cliente cliente = new Cliente("I");
				cliente.setS_codigo_letra_cliente("NO_DNI-");
				cliente.setSede_n_id_sede(servicioSede.listarId(sede).get());
				cliente.setN_codigo_cliente(generarCodigo() + 1);
				idCliente = servicioCliente.guardar(cliente);
			} else {
				if (servicioCliente.buscarClienteDni(dni)) {
					Cliente cliente = servicioCliente.obtenerClienteDni(dni);
					cliente.setS_estado_cliente("P");
					cliente.setN_codigo_cliente(dni);
					idCliente = servicioCliente.guardar(cliente);
				} else {
					String[] datos = reniec.checkDniApiReniec(String.valueOf(dni));
					if (datos.length == 0)
						return null;
					Cliente clienteDni = new Cliente("I");
					clienteDni.setSede_n_id_sede(servicioSede.listarId(sede).get());
					// Nombre reniec
					clienteDni.setS_nombre_cliente(datos[3] + " " + datos[1] + " " + datos[2]);
					// Edad
					clienteDni.setN_edad_cliente(reniec.getAgeFromDate(datos[4]));
					clienteDni.setN_dni_cliente(String.valueOf(dni));
					// Codigo generado por la primera letra del nombre y primer apellido
					clienteDni.setS_codigo_letra_cliente(datos[3].charAt(0) + ". " + datos[1]);
					// MOdificacion codigo para clientes no preferenciales
					// clienteDni.setN_id_usuario_asignado_cliente(generarCodigo() + 1);
					clienteDni.setN_codigo_cliente(dni);
					idCliente = servicioCliente.guardar(clienteDni);
				}
			}
		}
		// Registrar Atencion
		registrarAtencion(idCliente, sede, idEspecialidad, preferencial);
		return "redirect:/inicio";
	}

	public void registrarAtencion(int idCliente, int idSede, int idEspecialidad, int preferencial)
			throws ParseException, PrintException {
		// Realizar reservacion
		Reservacion reservacion = new Reservacion();
		// Verificar si es pereferencial
		if (preferencial == 1)
			reservacion.setS_preferencial_reservacion("A");
		reservacion.setCliente_n_id_cliente(servicioCliente.listarId(idCliente).get());
		reservacion.setSede_n_id_sede(servicioSede.listarId(idSede).get());
		int idReservacion = servicioReservacion.guardar(reservacion);
		// Relacionar especialidad a la reservacion
		if (preferencial != 1)
			registrarEspecialidadReservacion(idReservacion, idEspecialidad);
		// Imprimir
		String nombreSede = "";
		String codigoCliente = "";
		String fechaAtencion = "";
		String horaAtencion = "";
		// String numeroVentanilla = "";
		nombreSede = servicioSede.listarId(idSede).get().getS_nombre_sede();
		Cliente temp = servicioCliente.listarId(idCliente).get();
		codigoCliente = temp.getS_codigo_letra_cliente() + temp.getN_codigo_cliente();
		DateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatFecha.setTimeZone(TimeZone.getTimeZone("GMT"));
		fechaAtencion = dateFormatFecha.format(reservacion.getD_fecha_hora_reservacion());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		horaAtencion = dateFormat.format(reservacion.getD_fecha_hora_reservacion());
		// numeroVentanilla = ventanilla.getN_numero_ventanilla() + "";
		String texto = "\tP J\n" + "\tC.S.J. AREQUIPA\n\n" + "\tSede: " + nombreSede + "\n" + "\tCodigo: "
				+ codigoCliente + "\n" + "\tFecha: " + fechaAtencion + "\n" + "\tHora: " + horaAtencion // + "\n" +
																										// "Ventanilla:
																										// " +
																										// numeroVentanilla
				+ "\n" + " \n\n\n\n\n\n\n\n\n\n";

		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		DocPrintJob docPrintJob = printService.createPrintJob();
		Doc doc = new SimpleDoc(texto.getBytes(), flavor, null);

		docPrintJob.print(doc, null);

		// System.out.println(texto);

	}

	public void registrarEspecialidadReservacion(int idReservacion, int idEspecialidad) throws ParseException {
		EspecialidadReservacion especialidadReservacion = new EspecialidadReservacion();
		especialidadReservacion.setReservacion_n_id_reservacion(servicioReservacion.listarId(idReservacion).get());
		especialidadReservacion.setEspecialidad_n_id_especialidad(servicioEspecialidad.listarId(idEspecialidad).get());
		servicioEspecialidadReservacion.guardar(especialidadReservacion);
	}

	// Otorga una atencion a una ventanilla de un sede
	@GetMapping("/otorgarAtencion/{idVentanilla}/{idSede}")
	public String mostrarReservaciones(Authentication auth, @PathVariable int idVentanilla, @PathVariable int idSede,
			Model modelo) throws ParseException {
		List<Reservacion> reservaciones = new ArrayList<Reservacion>();
		Usuario usuario = servicioUsuario.encontrarUsuario(auth.getName()).get(0);
		// Verificar si la ventanilla es preferencial
		if (servicioVentanilla.listarId(idVentanilla).get().getS_preferencial_ventanilla().equals("A")) {
			reservaciones = servicioReservacion.listarPreferencial(idSede);
			// Listar reservaciones si no se encontraron preferenciales
			if (reservaciones.size() == 0)
				reservaciones = servicioReservacion.listarPorEspecialidadDeVentanilla(idVentanilla, idSede);
		} else {
			// Listar reservaciones
			reservaciones = servicioReservacion.listarPorEspecialidadDeVentanilla(idVentanilla, idSede);
		}
		if (reservaciones.size() != 0) {
			Atencion atencion = new Atencion();
			atencion.setUsuario_n_id_usuario(usuario);
			atencion.setVentanilla_n_id_ventanilla(servicioVentanilla.listarId(idVentanilla).get());
			// Cambiar estado de la reservacion
			Reservacion reservacion = reservaciones.get(0);
			reservacion.setS_estado_reservacion("I");
			servicioReservacion.guardar(reservacion);
			atencion.setReservacion_n_id_reservacion(reservacion);
			atencion.setS_estado_atencion("P");
			// Guardar atencion
			servicioAtencion.guardar(atencion);
		}
		return "VistaInicio_Calculadora";
	}

	// Otorga una atencion a una ventanilla de un sede en base a una especialidad
	// que quiere ayudar
	@GetMapping("/otorgarAtencion/ayuda/{idVentanilla}/{idSede}/{idEspecialidad}")
	public String otorgarAtencionAyuda(Authentication auth, @PathVariable int idVentanilla, @PathVariable int idSede,
			@PathVariable int idEspecialidad, Model modelo) throws ParseException {
		List<Reservacion> reservaciones = new ArrayList<Reservacion>();
		Usuario usuario = servicioUsuario.encontrarUsuario(auth.getName()).get(0);
		// Comprobar si se esta buscando ayuda de una ventanilla con preferencial
		if (idEspecialidad == -1) {
			// Obtener lista de reservaciones por reservacion preferencial
			reservaciones = servicioReservacion.listarPreferencial(idSede);
		} else {
			// Obtener lista de reservaciones de la especialidad solicitada
			reservaciones = servicioReservacion.listarPorEspecialidad(idSede, idEspecialidad);
		}
		// Verificar si hay reservaciones
		if (reservaciones.size() != 0) {
			// Crear atencion en base a la primera reservacion
			Atencion atencion = new Atencion();
			atencion.setUsuario_n_id_usuario(usuario);
			atencion.setVentanilla_n_id_ventanilla(servicioVentanilla.listarId(idVentanilla).get());
			// Cambiar estado de la reservacion
			Reservacion reservacion = reservaciones.get(0);
			reservacion.setS_estado_reservacion("I");
			servicioReservacion.guardar(reservacion);
			atencion.setReservacion_n_id_reservacion(reservacion);
			atencion.setS_estado_atencion("P");
			// Guardar atencion
			servicioAtencion.guardar(atencion);
		}
		return "VistaInicio_Calculadora";
	}

	@GetMapping("/atender/{idAtencion}")
	public String atenderCliente(Authentication auth, @PathVariable int idAtencion, Model modelo) {
		Atencion atencion = servicioAtencion.listarId(idAtencion).get();
		atencion.setS_estado_atencion("I");
		atencion.setS_asistir_atencion("I");
		atencion.setS_ausente_atencion("A");
		servicioAtencion.guardar(atencion);
		return "VistaInicio_Especialidad";
	}

	@GetMapping("/atender/fin/{idAtencion}")
	public String atenderClienteFin(Authentication auth, @PathVariable int idAtencion, Model modelo)
			throws ParseException {
		Atencion atencion = servicioAtencion.listarId(idAtencion).get();
		atencion.setS_estado_atencion("I");
		atencion.setS_asistir_atencion("I");
		// Generar fecha y hora actual para finalizar atencion
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
		atencion.setD_fecha_fin_atencion(dateParser.parse(timeStamp));
		servicioAtencion.guardar(atencion);
		return "VistaInicio_Especialidad";
	}

	@GetMapping("/atender/sonido/{idAtencion}")
	public String atenderClienteSonido(@PathVariable int idAtencion) {
		Atencion atencion = servicioAtencion.listarId(idAtencion).get();
		atencion.setS_asistir_atencion("P");
		servicioAtencion.guardar(atencion);
		return "VistaInicio_Especialidad";
	}

	@GetMapping("/atender/{idAtencion}/asistir")
	public String asistirAtencion(Authentication auth, @PathVariable int idAtencion, Model modelo) {
		Atencion atencion = servicioAtencion.listarId(idAtencion).get();
		atencion.setS_asistir_atencion("A");
		servicioAtencion.guardar(atencion);
		return "VistaInicio_Especialidad";
	}

	@GetMapping("/especialidad/{idSede}")
	public String listarEspecialidad(@PathVariable int idSede, Model modelo) {
		List<Especialidad> especialidades = servicioEspecialidad.listarPorSede(idSede);
		modelo.addAttribute("especialidades", especialidades);
		return "VistaInicio_Especialidad";
	}

	@GetMapping("/calculadora/{idEspecialidad}")
	public String mostrarCalculadora(@PathVariable int idEspecialidad, Model modelo) {
		modelo.addAttribute("especialidad", idEspecialidad);
		return "VistaInicio_Calculadora";
	}

}
