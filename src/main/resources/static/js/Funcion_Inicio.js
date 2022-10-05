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
	location.href = "/inicio/especialidad/" + $("#sede_inicio").val();
}

//CALCULADORA


/* ------------------------------------------------------------------------------------------------ */
/*                              Funcion para obtener el output y para imprimir                      */
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */

function getOutput() {
	return document.getElementById("output-value").value;
}

function printOutput(num, clear) {
	var a = document.getElementById("output-value");
	if (a.value.length != 8 || clear) {
		a.value = num;
	}
}

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
/*                              Funcion para el backspace                                           */
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */

function reverseNumberFormat(num) {
	return Number(num.replace('0', ''));
}
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
/*                              Bucle para clear y back                                             */
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */

var operator = document.getElementsByClassName("operator");
for (var i = 0; i < operator.length; i++) {
	operator[i].addEventListener('click', function() {
		if (this.id == "clear") {
			printOutput("", true);
		}
		else if (this.id == "backspace") {
			var output = reverseNumberFormat(getOutput()).toString();
			if (output) {//if output has a value
				output = output.substr(0, output.length - 1);
				printOutput(output, true);
			}
		}
	});
}
var number = document.getElementsByClassName("number");
for (var i = 0; i < number.length; i++) {
	number[i].addEventListener('click', function() {
		var output = getOutput();
		if (output != NaN) { //if output is a number
			output = output + this.id;
			printOutput(output);
		}
	});
}
/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
