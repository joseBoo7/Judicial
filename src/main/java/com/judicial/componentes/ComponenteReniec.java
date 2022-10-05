package com.judicial.componentes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class ComponenteReniec {
	// Api Reniec http://172.28.206.57:8080/SIJ/Reniec/
	public String[] checkDniApiReniec(String dni) throws Exception {
		String data[] = {};
		URL url;
		try {
			url = new URL("http://172.28.206.57:8080/SIJ/Reniec/" + dni);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Accept", "application/json");
			http.setConnectTimeout(3000);//Tiempo de espera
			try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				// Extraemos el arreglo json del 'response'
				JSONArray jsonArray = new JSONArray(response.toString());
				//Guardamos el dni y los nombres
				data = new String[5];
				data[0] = jsonArray.getString(0).toString();//dni
				data[1] = jsonArray.getString(2).toString();//apellido paterno
				data[2] = jsonArray.getString(3).toString();//apellido materno
				data[3] = jsonArray.getString(5).toString();//nombres
				data[4] = jsonArray.getString(28).toString();//fecha de nacimiento
			} catch (Exception e) {
				System.out.println("Error de parseo");
			}
			http.disconnect();
		} catch (MalformedURLException e1) {
			System.out.println("Error de conexion");
		}
		return data;
	}
	
	public int getAgeFromDate(String fecha) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}
}
