function funcion_EliminarVentanillaEspecialidad(id, id2) {
	console.log(id);
	console.log(id2);
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se eliminara la especialidad de la ventanilla!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, eliminar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/ventanilla/especialidad/eliminar/" + id + "/" + id2
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Eliminado!',
				'Tu registro ha sido eliminado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/ventanilla/especialidad/" + id2;
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
function funcion_HabilitarVentanilla(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se habilitará la ventanilla!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, habilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/ventanilla/habilitar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Habilitado!',
				'Tu registro ha sido habilitado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/ventanilla";
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
function funcion_ValidarVentanillaEspecialidad() {
	if ($("#especialidad_ventanillaEspecialidad").val() == 0) {

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
function funcion_EditarVentanillaEspecialidad(id) {
	var bandera = funcion_ValidarVentanillaEspecialidad();
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
						n_id_ventanilla_has_especialidad: parseInt($("#id_ventanillaEspecialidad").val()),
						Ventanilla_n_id_ventanilla: parseInt($("#ventanilla_ventanillaEspecialidad").val()),
						Especialidad_n_id_especialidad: parseInt($("#especialidad_ventanillaEspecialidad").val()),
					},
					url: "/ventanilla/especialidad/guardar/" + id,
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
				'exitosamente'
			).then((result) => {
				if (result.isConfirmed) {
					location.href = "/ventanilla/especialidad/" + id;
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