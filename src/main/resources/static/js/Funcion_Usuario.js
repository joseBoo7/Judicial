function funcion_EliminarUsuario(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se inhabilitará el usuario!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, inhabilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/usuario/eliminar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Inhabilitado!',
				'Tu registro ha sido inhabilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/usuario";
				}
			});
		} else if (
			/* Read more about handling dismissals below */
			result.dismiss === Swal.DismissReason.cancel
		) {
			swalWithBootstrapButtons.fire(
				'Cancelado',
				'No se realizo la acción',
				'error'
			)
		}
	})
}
function funcion_HabilitarUsuario(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se habilitará el usuario!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, habilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/usuario/habilitar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Habilitado!',
				'Tu registro ha sido habilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/usuario";
				}
			});
		} else if (
			/* Read more about handling dismissals below */
			result.dismiss === Swal.DismissReason.cancel
		) {
			swalWithBootstrapButtons.fire(
				'Cancelado',
				'No se realizo la acción',
				'error'
			)
		}
	})
}
function funcion_ValidarUsuario() {
	if ($("#nombre_usuario").val().length == 0 || $("#nombre_usuario").val().length > 100
		|| $("#apellido_usuario").val().length == 0 || $("#apellido_usuario").val().length > 150
		|| $("#dni_usuario").val().length == 0 || $("#dni_usuario").val().length > 8
		|| $("#sede_usuario").val() == -1 || $("#rol_usuario").val() == -1 || $("#clave_usuario").val().length < 6) {

		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})
		swalWithBootstrapButtons.fire(
			'Error',
			'Llene correctamente los campos',
			'error'
		)
		return true;
	}
}
function funcion_EditarUsuario() {
	var bandera = funcion_ValidarUsuario();
	if (bandera) {
		return;
	}
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se guardara el usuario!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, guardar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					data: {
						n_id_usuario: $("#id_usuario").val(),
						s_estado_usuario: $("#estado_usuario").val(),
						s_nombre_usuario: $("#nombre_usuario").val().toUpperCase(),
						s_apellidos_usuario: $("#apellido_usuario").val().toUpperCase(),
						s_dni_usuario: $("#dni_usuario").val(),
						Sede_n_id_sede: $("#sede_usuario").val(),
						Rol_n_id_rol: $("#rol_usuario").val(),
						s_usuario_usuario: $("#usuario_usuario").val().toUpperCase(),
						s_contrasena_usuario: $("#clave_usuario").val(),
					},
					url: "/usuario/guardar",
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/usuario";
				}
			});
		} else if (
			/* Read more about handling dismissals below */
			result.dismiss === Swal.DismissReason.cancel
		) {
			swalWithBootstrapButtons.fire(
				'Cancelado',
				'No se realizo la acción',
				'error'
			)
		}
	})
}
function funcion_EditarUsuarioEditar() {
	var bandera = funcion_ValidarUsuario();
	if (bandera) {
		return;
	}
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se guardara el usuario!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, guardar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					data: {
						n_id_usuario: $("#id_usuario").val(),
						s_estado_usuario: $("#estado_usuario").val(),
						s_nombre_usuario: $("#nombre_usuario").val().toUpperCase(),
						s_apellidos_usuario: $("#apellido_usuario").val().toUpperCase(),
						s_dni_usuario: $("#dni_usuario").val(),
						Sede_n_id_sede: $("#sede_usuario").val(),
						Rol_n_id_rol: $("#rol_usuario").val(),
						s_usuario_usuario: $("#usuario_usuario").val().toUpperCase(),
						s_contrasena_usuario: $("#clave_usuario").val(),
					},
					url: "/usuario/guardarEditado",
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/usuario";
				}
			});
		} else if (
			/* Read more about handling dismissals below */
			result.dismiss === Swal.DismissReason.cancel
		) {
			swalWithBootstrapButtons.fire(
				'Cancelado',
				'No se realizo la acción',
				'error'
			)
		}
	})
}
//Funciones
function valideKey(evt) {

	// code is the decimal ASCII representation of the pressed key.
	var code = (evt.which) ? evt.which : evt.keyCode;

	if (code == 8) { // backspace.
		return true;
	} else if (code >= 48 && code <= 57) { // is a number.
		return true;
	} else { // other keys.
		return false;
	}
}

// Consulta RENIEC

function funcion_ValidarReniec() {
	if ($("#dni_usuario").val().length == 0 || $("#dni_usuario").val().length > 8
		|| $("#dni_usuario").val().length < 8) {
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})
		swalWithBootstrapButtons.fire(
			'Error',
			'INGRESE DNI',
			'error'
		)

		return true;
	}
}

function funcion_reniec() {
	var bandera = funcion_ValidarReniec();
	if (bandera) {
		return;
	}

	$.ajax({
		type: 'GET',
		url: '/api/reniec/' + $("#dni_usuario").val(),
		success: function(data) {
			if (data == '') {
				const swalWithBootstrapButtons = Swal.mixin({
					customClass: {
						confirmButton: 'btn btn-success',
						cancelButton: 'btn btn-danger'
					},
					buttonsStyling: false
				})
				swalWithBootstrapButtons.fire(
					'Error',
					'Error de conexión RENIEC',
					'error'
				)
			}
			else {
				document.getElementById("nombre_usuario").value = data.nombres;
				document.getElementById("apellido_usuario").value = data.paterno + " " + data.materno;
				document.getElementById("usuario_usuario").value = data.usuario;
			}
		}
	});
}

