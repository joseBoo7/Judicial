package com.judicial.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazUsuarioServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Usuario;
import com.judicial.modelo.Ventanilla;

@Controller
public class ControladorPrincipal {

	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;
	@Autowired
	private InterfazUsuarioServicio servicioUsuario;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;

	//Prueba ojo °
	@GetMapping("/prueba")
	public String websocket() {
		return "prueba";
	}
	
	@GetMapping("/")
	public String listar() {
		return "VistaPrincipal";
	}

	@GetMapping("/login")
	public String prueba() {
		return "VistaPrincipal";
	}

	@GetMapping("/principal")
	public String principal(Authentication auth, Model modelo) {
		String valor = "";
		for (Object o : auth.getAuthorities()) {
			valor = o.toString();
			break;
		}
		if (valor.equals("ROLE_ADMINISTRADOR")) {
			modelo.addAttribute("nombre", auth.getName());
			return "VistaPrincipal_Admin";
		} else {
			List<Usuario> usuario = null;
			usuario = servicioUsuario.encontrarUsuario(auth.getName());
			if (usuario.size() == 0)
				throw new UsernameNotFoundException("Usuario inválidos");
			int idSede = usuario.get(0).getSede_n_id_sede().getN_id_sede();
			Sede sede = servicioSede.listarId(usuario.get(0).getSede_n_id_sede().getN_id_sede()).get();
			List<Ventanilla> datos = servicioVentanilla.ventanillasPorSede(idSede);
			modelo.addAttribute("idSede", idSede);
			modelo.addAttribute("nombre", sede.getS_nombre_sede());
			modelo.addAttribute("ventanillas", datos);
			return "VistaPrincipal_Ventanilla";
		}
	}

	@GetMapping("/inicio/{idSede}/{idVen}")
	public String principalSedeVentanilla(Authentication auth, @PathVariable int idSede, @PathVariable int idVen,
			Model modelo) {
		modelo.addAttribute("nombre", auth.getName());
		modelo.addAttribute("sede", servicioSede.listarId(idSede).get().getS_nombre_sede());
		modelo.addAttribute("idSede", servicioSede.listarId(idSede).get().getN_id_sede());
		modelo.addAttribute("idVentanilla", servicioVentanilla.listarId(idVen).get().getN_id_ventanilla());
		modelo.addAttribute("ventanilla", servicioVentanilla.listarId(idVen).get().getN_numero_ventanilla());
		modelo.addAttribute("especialidades", servicioEspecialidad.listarPorSede(idSede));
		return "VistaPrincipal_Principal";
	}

	@GetMapping("/registro")
	public String registrar() {
		return "VistaPrincipal_registro";
	}

	@GetMapping("/cerrar")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return "redirect:/";// para redirigir a la pantalla de login
	}
}
