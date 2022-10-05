function funcion_EliminarEspecialidad(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se inhabilitará la especialidad!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, inhabilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/especialidad/eliminar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Inhabilitado!',
				'Tu registro ha sido inhabilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/especialidad";
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
function funcion_HabilitarEspecialidad(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se habilitará la especialidad!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, habilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/especialidad/habilitar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Habilitado!',
				'Tu registro ha sido habilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/especialidad";
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
function funcion_ValidarEspecialidad() {
	if ($("#nombre_especialidad").val().length == 0
		|| $("#nombre_especialidad").val().length > 150 || $("#sede_especialidad").val() == 0
	) {

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
function funcion_EditarEspecialidad() {
	var bandera = funcion_ValidarEspecialidad();
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
		text: "¡Se guardara la especialidad!",
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
						n_id_especialidad: $("#id_especialidad").val(),
						s_estado_especialidad: $("#estado_especialidad").val(),
						s_nombre_especialidad: $("#nombre_especialidad").val().toUpperCase(),
						Sede_n_id_sede: $("#sede_especialidad").val(),
					},
					url: "/especialidad/guardar",
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/especialidad";
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