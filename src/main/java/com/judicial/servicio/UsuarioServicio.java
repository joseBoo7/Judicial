package com.judicial.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazUsuario;
import com.judicial.interfacesServicio.InterfazUsuarioServicio;
import com.judicial.modelo.Usuario;

@Service
public class UsuarioServicio implements InterfazUsuarioServicio, UserDetailsService {

	@Autowired
	private InterfazUsuario dato;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> listar() {
		return (List<Usuario>) dato.findAll();
	}

	@Override
	public Optional<Usuario> listarId(int id) {
		return dato.findById(id);
	}

	public int guardar(Usuario u) {
		int respuesta = 0;
		u.setS_contrasena_usuario(passwordEncoder.encode(u.getS_contrasena_usuario()));
		Usuario usuario = dato.save(u);
		if (!usuario.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuario = encontrarUsuario(username);
		if (usuario.size() == 0) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		//
		List<GrantedAuthority> roles = new ArrayList<>();
		//Importante para determinar los roles
		roles.add(new SimpleGrantedAuthority("ROLE_"+usuario.get(0).getRol_n_id_rol().getS_nombre_rol()));
		UserDetails userDetail = new User(usuario.get(0).getS_usuario_usuario(), usuario.get(0).getS_contrasena_usuario(), roles);
		return userDetail;
	}

	@Override
	public int guardarEdit(Usuario u) {
		int respuesta = 0;
		Usuario usuario = dato.save(u);
		if (!usuario.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public List<Usuario> encontrarUsuario(String nombre) {
		return dato.findByUsuarioName(nombre);
	}

}
