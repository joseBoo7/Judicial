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
	//console.log(message);
	if (message.length >= 6) {
		var dato = "";
		for (var i = 0; i < 6; i = i + 2) {
			if (message.length % 2 == 0) {
				const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div><div class='sec_1 col " + this.color(message[i + 1]) + "'><h5> " + this.showValue(message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i + 1].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
				dato = dato + con;
			} else {
				if (i == message.length - 1) {
					const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
					dato = dato + con;
				} else {
					const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div><div class='sec_1 col " + this.color(message[i + 1]) + "'><h5> " + this.showValue(message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i + 1].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
					dato = dato + con;
				}
			}
		}
		let div = document.getElementById("data");
		div.innerHTML = dato;
	} else {
		var dato = "";
		for (var i = 0; i < message.length; i = i + 2) {
			if (message.length % 2 == 0) {
				const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div><div class='sec_1 col " + this.color(message[i + 1]) + "'><h5> " + this.showValue(message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i + 1].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
				dato = dato + con;
			} else {
				if (i == message.length - 1) {
					const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
					dato = dato + con;
				} else {
					const con = new String("<div class='row'><div class='sec_1 col " + this.color(message[i]) + "'><h5> " + this.showValue(message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div><div class='sec_1 col " + this.color(message[i + 1]) + "'><h5> " + this.showValue(message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.s_codigo_letra_cliente, message[i + 1].reservacion_n_id_reservacion.cliente_n_id_cliente.n_codigo_cliente, message[i + 1].ventanilla_n_id_ventanilla.n_numero_ventanilla) + "</h5></div></div>");
					dato = dato + con;
				}
			}
		}
		let div = document.getElementById("data");
		div.innerHTML = dato;
	}
}

function showValue(letra, codigo, ventanilla) {
	return letra + codigo + " -> VENTANILLA " + ventanilla;
}

function color(estado) {
	// Verifica si se puede el estado esta en activo
	if (estado.s_asistir_atencion == 'A') {
		// Reproducir sonido 3 veces
		const sonido = new Audio("/sounds/EfectS.mp3");
		sonido.play();
		// Cambia el estado del sonido
		$.ajax({

			url: "/inicio/atender/sonido/" + estado.n_id_atencion,

			success: function() {
				console.log("Cambio de estado");
			}
		});
	}

	// Comprobacion borde 
	if (estado.s_asistir_atencion == 'A' || estado.s_asistir_atencion == 'P') {
		return "borde"
	}
	else
		return "border border-secondar"
}

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
			showGreeting2(JSON.parse(greeting.body));
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

function showGreeting2(message) {
	var dato = "";
	console.log(message);
	for (var i = 0; i < message.length; i++) {
		const con = new String("<div class='test__grid border border-2  shadow-lg bg-body rounded'><h4>" + message[i].cliente_n_id_cliente.s_codigo_letra_cliente + " " + message[i].cliente_n_id_cliente.n_codigo_cliente + "</h4></div>");
		dato = dato + con;
	}
	let div = document.getElementById("dataReservacion");
	div.innerHTML = dato;
}

this.connectReservacion();