function funcion_EliminarSede(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se inhabilitará la sede!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, inhabilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/sede/eliminar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Inhabilitado!',
				'Tu registro ha sido inhabilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/sede";
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
function funcion_HabilitarSede(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se habilitará la sede!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, habilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/sede/habilitar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Habilitado!',
				'Tu registro ha sido habilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/sede";
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
function funcion_ValidarSede() {
	if ($("#nombre_sede").val().length == 0 || $("#nombre_sede").val().length > 100
		|| $("#ubicacion_sede").val().length == 0 || $("#ubicacion_sede").val().length > 100) {

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
function funcion_EditarSede() {
	var bandera = funcion_ValidarSede();
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
		text: "¡Se guardara la sede!",
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
						n_id_sede: $("#id_sede").val(),
						s_estado_sede: $("#estado_sede").val(),
						s_nombre_sede: $("#nombre_sede").val().toUpperCase(),
						s_ubicacion_sede: $("#ubicacion_sede").val().toUpperCase(),
					},
					url: "/sede/guardar",
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/sede";
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