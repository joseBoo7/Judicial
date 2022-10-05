function funcion_Preferencial() {
	//connect();
	$.ajax({

		url: '/inicio/registrar/' + localStorage.getItem("sede") + '/12/1/0',

		success: function() {
			sendName();
			submitResult();
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

	}).then(function() {
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
		stompClient.subscribe('/topic/greetings2');
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

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() {
		connect();
		sendName();
	});
});


this.connect();
