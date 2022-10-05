
function funcion_atras(){
	location.href = "/inicio/especialidad/" + localStorage.getItem("sede");
}

function funcion_EnviarDni(idEspecialidad) {
	if (document.getElementById("output-value").value.length == 8) {
		//connect();
		$.ajax({

			url: "/inicio/registrar/" + localStorage.getItem("sede") + "/" + idEspecialidad + "/0/" + parseInt(document.getElementById("output-value").value),

			success: function() {
				sendName();
				submitResult();
				//location.href = "/inicio/especialidad/" + localStorage.getItem("sede");
			},
			error: function() {
				location.href = "/inicio/especialidad/" + localStorage.getItem("sede");;
			}
		});
	}
}
function funcion_EnviarDni_Enter(e, idEspecialidad) {
	if (e.keyCode === 13 && !e.shiftKey) {
		this.funcion_EnviarDni(idEspecialidad);
	}
}
function funcion_sinDni(idEspecialidad) {
	//connect();
	$.ajax({

		url: "/inicio/registrar/" + localStorage.getItem("sede") + "/" + idEspecialidad + "/0/0",

		success: function() {
			sendName();
			submitResult();
			//location.href = "/inicio/especialidad/" + localStorage.getItem("sede");
		}
	});
}

//Alerta
function submitResult() {
	Swal.fire({
		title: 'Bienvenido',
		timer: 3000,                       // Establece el tiempo de duracion
		html: '<b> Espere su turno </b>',
		showConfirmButton: false

	}).then(function(result) {
		if (true) {
			location.href = "/inicio/especialidad/" + localStorage.getItem("sede"); // Redirecciona a la interfaz cuando termina el temporizador
			// en mi caso la interfaz de especialidad lo tengo ubicado de 
			// esta manera ../Especialidad/especialidad.html
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
		stompClient.subscribe('/topic/greetings2', function(greeting) {
			showGreeting(JSON.parse(greeting.body));
		});
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
	stompClient.send("/app/hello2", {}, JSON.stringify({ 'id': localStorage.getItem("sede") }));
}

function showGreeting(message) {
	for (var i = 0; i < message.length; i++) {
		$("#greetings").append("<tr><td>" + message[i].s_nombre_cliente + "</td></tr>");
	}

}
$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
});


this.connect();



