function funcion_atender() {
	$.ajax({

		url: "/inicio/atender/" + $("#codigo").val(),

		success: function() {
			sendName();
		}
	});
}

function funcion_atender_fin() {
	$.ajax({

		url: "/inicio/atender/fin/" + $("#codigo").val(),

		success: function() {
			sendName();
		}
	});
}

function funcion_otorgarAtencion(idVentanilla) {
	$.ajax({

		url: "/inicio/otorgarAtencion/" + idVentanilla + "/" + localStorage.getItem("sede"),

		success: function() {
			sendName();
			sendName2();
		}
	});
}
function funcion_ValidarEspecialidad() {
	if ($("#especialidad").val() == 0) {

		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})
		swalWithBootstrapButtons.fire(
			'Error',
			'Seleccione una especialidad',
			'error'
		)
		return true;
	}
}
function funcion_ayudar(idVentanilla) {
	var bandera = funcion_ValidarEspecialidad();
	if (bandera) {
		return;
	}
	$.ajax({

		url: "/inicio/otorgarAtencion/ayuda/" + idVentanilla + "/" + localStorage.getItem("sede") + "/" + $("#especialidad").val(),

		success: function() {
			sendName();
			sendName2();
		}
	});
}

/* WEBSOCKET------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */

var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
			showGreeting(JSON.parse(greeting.body));
		});
		//Consulta 
		sendName();
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/app/hello", {}, JSON.stringify({ 'id': localStorage.getItem("sede") }));
}

function showGreeting(message) {
	//
	var temp = [];
	for (var i in message) {
		if (message[i].ventanilla_n_id_ventanilla.n_id_ventanilla == parseInt(localStorage.getItem("ventanilla"))) {
			temp.push(message[i]);
		}
	}
	message = temp;
	//
	var btnLla = document.getElementById("llamar");
	var btnAyu = document.getElementById("llamarAyuda");
	var btnFin = document.getElementById("fin");
	var btnSig = document.getElementById("siguiente");
	if (message.length >= 1) {
		// Desactivar llamar atencion
		btnLla.style.display = "none";
		btnAyu.style.display = "none";
		// Verficar estado de sonido y color de borde
		if (message[0].s_asistir_atencion == 'I') {
			$.ajax({

				url: "/inicio/atender/" + message[0].n_id_atencion + "/asistir",

				success: function() {
					sendName();
				}
			});
		} else {
			//
			//x.style.display = "block";
			btnFin.style.display = "block";
			btnSig.style.display = "block";
			//
			var dato = "";
			const con = new String("<input id='codigo' value='" + message[0].n_id_atencion + "' type='hidden'><p class='card-text'>" + message[0].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente + " " + message[0].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente + "</p>");
			dato = dato + con;
			let div = document.getElementById("data");
			div.innerHTML = dato;
		}

	} else {
		// Activar llamar atencion
		btnLla.style.display = "block";
		btnAyu.style.display = "block";
		//x.style.display = "none";
		btnFin.style.display = "none";
		btnSig.style.display = "none";
		//
		var dato = "";
		const con = new String("");
		dato = dato + con;
		let div = document.getElementById("data");
		div.innerHTML = dato;
	}
}

function showValue(letra, codigo) {
	return letra + codigo;
}

$(function() {
	$("#finalizar").click(function() { funcion_atender(); });
});

this.connect();

/* WEBSOCKET RESERVACION------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */

var stompClient2 = null;

function setConnected2(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connectReservacion() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient2 = Stomp.over(socket);
	stompClient2.connect({}, function(frame) {
		setConnected2(true);
		console.log('Connected: ' + frame);
		stompClient2.subscribe('/topic/greetings2', function(greeting) {
		});
		sendName2();
	});
}

function disconnect2() {
	if (stompClient2 !== null) {
		stompClient2.disconnect();
	}
	setConnected2(false);
	console.log("Disconnected");
}

function sendName2() {
	stompClient2.send("/app/hello2", {}, JSON.stringify({ 'id': localStorage.getItem("sede") }));
}

this.connectReservacion();