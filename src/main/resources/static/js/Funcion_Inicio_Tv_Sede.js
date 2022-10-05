function funcion_Validar() {
	if ($("#sede_inicio").val() == 0) {

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
function funcion_EnviarSede() {
	var bandera = funcion_Validar();
	if (bandera) {
		return;
	}
	//Guardar localmente en el localStorage el codigo de la sede
	localStorage.setItem("sede", $("#sede_inicio").val());
	//Enlace especialidad
	location.href = "/inicio/tv/" + localStorage.getItem("sede");
}