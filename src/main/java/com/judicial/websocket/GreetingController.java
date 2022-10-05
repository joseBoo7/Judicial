package com.judicial.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.judicial.interfacesServicio.InterfazAtencionServicio;
import com.judicial.interfacesServicio.InterfazReservacionServicio;
import com.judicial.modelo.Atencion;
import com.judicial.modelo.Reservacion;

@Controller
public class GreetingController {
	@Autowired
	public InterfazAtencionServicio servicioAtencion;
	@Autowired
	public InterfazReservacionServicio servicioReservacion;

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public List<Atencion> greeting(HelloMessage message) throws Exception {
		return servicioAtencion.listarPorSede(message.getId());
	}

	@MessageMapping("/hello2")
	@SendTo("/topic/greetings2")
	public List<Reservacion> greeting2(HelloMessage message) throws Exception {
		return servicioReservacion.listarPorSede(message.getId());
	}

}
