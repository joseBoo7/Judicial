function mayusculas(e) {
	e.value = e.value.toUpperCase();
}
function funcion_ValidarSede() {
	if ($("#sede").val() == 0
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
			'Seleccione una sede',
			'error'
		)
		return true;
	}
}
function funcion_EnviarSede() {
	var bandera = funcion_ValidarSede();
	if (bandera) {
		return;
	}
	location.href = "/principal/ventanilla/" + $("#sede").val();

}
//
function funcion_ValidarVentanilla() {
	if ($("#ventanilla").val() == 0
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
			'Seleccione una ventanilla',
			'error'
		)
		return true;
	}
}
function funcion_EnviarVentanilla(idSede) {
	var bandera = funcion_ValidarVentanilla();
	if (bandera) {
		return;
	}
	//Guardar localmente en el localStorage el codigo de la ventanilla
	localStorage.setItem("ventanilla", $("#ventanilla").val());
	
	location.href = "/inicio/" + idSede + "/" + $("#ventanilla").val();

}