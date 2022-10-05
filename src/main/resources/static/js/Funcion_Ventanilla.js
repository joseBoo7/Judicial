function funcion_EliminarVentanilla(id) {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '¿Esta seguro?',
		text: "¡Se inhabilitará la ventanilla!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Si, inhabilitar!',
		cancelButtonText: 'No, cancelar!',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax(
				{
					url: "/ventanilla/eliminar/" + id
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Inhabilitado!',
				'Tu registro ha sido inhabilitado',
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
function funcion_ValidarVentanilla() {
	if ($("#numero_ventanilla").val().length == 0 || $("#sede_ventanilla").val() == 0) {

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
function funcion_EditarVentanilla() {
	var preferencial
	if (document.getElementById("preferencial_ventanilla").checked) {
		preferencial = "A"
	} else {
		preferencial = "I"
	}
	var bandera = funcion_ValidarVentanilla();
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
		text: "¡Se guardara la ventanilla!",
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
						n_id_ventanilla: $("#id_ventanilla").val(),
						s_estado_ventanilla: $("#estado_ventanilla").val(),
						n_numero_ventanilla: $("#numero_ventanilla").val(),
						s_preferencial_ventanilla: preferencial,
						Sede_n_id_sede: $("#sede_ventanilla").val(),
					},
					url: "/ventanilla/guardar",
					type: "POST",
				}
			);
			swalWithBootstrapButtons.fire(
				'¡Realizado!',
				'Tu registro ha sido registrado',
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