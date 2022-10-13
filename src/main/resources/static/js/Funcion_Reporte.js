
function funcion_especialidad() {
	$.ajax({

		url: "/api/especialidad/" + $("#sede").val(),

		success: function(data) {
			var dato = "<select id='especialidad' class='form-select' aria-label='Default select example'><option selected value='-1'>-- TODAS LAS ESPECIALIDAD --</option><option value='0'>-- PREFERENCIAL --</option>";
			for (var i = 0; i < data.length; i++) {
				const con = new String("<option value='" + data[i].n_id_especialidad + "'>" + data[i].s_nombre_especialidad + "</option>");
				dato = dato + con;
			}
			const con = new String("</select>");
			dato = dato + con;
			let div = document.getElementById("especialidades");
			div.innerHTML = dato;
		}
	});
}

function funcion_reporte() {
	$.ajax({
		data: JSON.stringify({
			"f1": $("#date_1").val(),
			"f2": $("#date_2").val(),
			"sede": $("#sede").val(),
			"especialidad": $("#especialidad").val()
		}),
		type: 'POST',
		dataType: "json",
		contentType: 'application/json;charset=UTF-8',
		url: "/api/reporte",
		success: function(data) {
			var tabla = document.getElementById("tabla");
			if (data.length != 0) {
				tabla.style.display = "block";
				var dato = "";

				for (var i = 0; i < data.length; i++) {
					//Valores tiempo
					var t1 = parseFloat(data[i].tiempo1);
					var t2 = data[i].tiempo2;
					if (t2 == null)
						t2 = 0;
					else {
						t2 = parseFloat(data[i].tiempo2);
					}
					//
					const con = new String("<tr><th>" + (i + 1) + "</th><td>" + data[i].fecha + "</td><td>" + data[i].nombre + "</td><td>" + funcion_nombre_estado(data[i].estado) + "</td><td>" + secondsFormat(t1.toFixed(0)) + "</td><td>" + secondsFormat(t2.toFixed(0)) + "</td><td>" + secondsFormat((t1 + t2).toFixed(0)) + "</td><td><button type='button'  onclick='funcion_reporte_modal(\"" + data[i].fecha+"\",\""+ data[i].estado + "\")' data-bs-toggle='modal' data-bs-target='#infoModal' ><img src='/icons/info.svg' alt='info' srcset=''></button></td></tr>");
					dato = dato + con;

				}
				/*const elementos = data.sort();
				let valores = [];
				let cont = 1;
				for (let i = 0; i < elementos.length; i++) {
					//Calculo de repetidos
					if (elementos[i + 1].s_nombre_usuario === elementos[i].s_nombre_usuario) {
						cont++;
					} else {
						valores.push({ type: elementos[i].s_nombre_usuario, earnings: cont });
						cont = 1;
					}
				}*/
				let div = document.getElementById("dataReport");
				div.innerHTML = dato;
				//consulta
				funcion_reporte_usuario();
				funcion_reporte_ventanilla();
				funcion_reporte_promedio();
			} else {
				tabla.style.display = "none";
			}
		}
	});
}

// Reporte Datos Modal

function funcion_reporte_modal(fecha,ausente) {
	$.ajax({
		data: JSON.stringify({
			"f1": fecha,
			"f2": ausente,
			"sede": $("#sede").val(),
			"especialidad": $("#especialidad").val()
		}),
		type: 'POST',
		dataType: "json",
		contentType: 'application/json;charset=UTF-8',
		url: "/api/reporte/modal",
		success: function(data) {
			var tabla = document.getElementById("tablaModal");
			if (data.length != 0) {
				tabla.style.display = "block";
				var dato = "";

				for (var i = 0; i < data.length; i++) {
					//Valores tiempo
					var t1 = parseFloat(data[i].tiempo1);
					var t2 = data[i].tiempo2;
					if (t2 == null)
						t2 = 0;
					else {
						t2 = parseFloat(data[i].tiempo2);
					}
					//
					const con = new String("<tr><th>" + (i + 1) + "</th><td>" + data[i].nombre + "</td><td>" + data[i].ventanilla + "</td><td>" + secondsFormat(t1.toFixed(0)) + "</td><td>" + secondsFormat(t2.toFixed(0)) + "</td><td>" + secondsFormat((t1 + t2).toFixed(0)) + "</td></tr>");
					dato = dato + con;

				}
				let div = document.getElementById("dataModal");
				div.innerHTML = dato;
			} else {
				tabla.style.display = "none";
			}
		}
	});
}

//Funcion para mostrar hora formateada
function secondsFormat(s) {
	var day = Math.floor(s / (24 * 3600)); // Math.floor () redondea hacia abajo 
	var hour = Math.floor((s - day * 24 * 3600) / 3600);
	var minute = Math.floor((s - day * 24 * 3600 - hour * 3600) / 60);
	var second = s - day * 24 * 3600 - hour * 3600 - minute * 60;
	return minute + "' " + second + "''";
}

function funcion_reporte_usuario() {
	$.ajax({
		data: JSON.stringify({
			"f1": $("#date_1").val(),
			"f2": $("#date_2").val(),
			"sede": $("#sede").val(),
			"especialidad": $("#especialidad").val()
		}),
		type: 'POST',
		dataType: "json",
		contentType: 'application/json;charset=UTF-8',
		url: "/api/reporte/usuario",
		success: function(data) {
			chart1.data = data;
			chart1.update();
		}
	});
}

var data1 = [];
//Grafico de barras
const grapBar = {
	container: document.getElementById('graficoBar'),
	autoSize: true,
	data: data1,
	theme: {
		overrides: {
			bar: {
				series: {
					strokeWidth: 0,
				},
			},
		},
	},
	title: {
		text: 'Cantidad de atenciones por usuario',
		fontSize: 18,
	},
	subtitle: {
		text: 'Atención de usuarios',
	},
	series: [
		{
			type: 'bar',
			xKey: 'nombre',
			yKey: 'valor',
		},
	],
	axes: [
		{
			type: 'category',
			position: 'left',
		},
		{
			type: 'number',
			position: 'bottom',
			title: {
				enabled: true,
				text: 'usuario/cantidad',
			},
		},
	],
	legend: {
		enabled: false,
	},
};

var chart1 = agCharts.AgChart.create(grapBar);

function funcion_nombre_estado(nom) {
	if (nom == "I")
		return "FINALIZADO"
	if (nom == "A")
		return "AUSENTE"
}

function funcion_reporte_ventanilla() {
	$.ajax({
		data: JSON.stringify({
			"f1": $("#date_1").val(),
			"f2": $("#date_2").val(),
			"sede": $("#sede").val(),
			"especialidad": $("#especialidad").val()
		}),
		type: 'POST',
		dataType: "json",
		contentType: 'application/json;charset=UTF-8',
		url: "/api/reporte/ventanilla",
		success: function(data) {
			chart2.data = data;
			chart2.update();
		}
	});
}
var data2 = [];
//Grafico circular
const grapCircular = {
	container: document.getElementById('graficoCircular'),
	autoSize: true,
	title: {
		text: 'Grafico Circular',
		fontSize: 18,
	},
	subtitle: {
		text: '',
	},
	series: [
		{
			data: data2,
			type: 'pie',
			labelKey: 'nombre',
			angleKey: 'valor',
			label: {
				minAngle: 0,
			},
			callout: {
				strokeWidth: 2,
			},
			fills: [
				'#31b56a',
				'#f0932b',
				'#eb4c4b',
				'#6ab04c',
				'#7ed6df',
				'#febe76',
				'#ff7979',
				'#badc58',
				'#f9ca23',
			],
			strokes: [
				'#31b56a',
				'#a8671e',
				'#a43535',
				'#4a7b35',
				'#58969c',
				'#b28553',
				'#b35555',
				'#829a3e',
				'#ae8d19',
			],
		},
	],
	legend: {
		enabled: false,
	},
};

var chart2 = agCharts.AgChart.create(grapCircular);

function funcion_reporte_promedio() {
	$.ajax({
		data: JSON.stringify({
			"f1": $("#date_1").val(),
			"f2": $("#date_2").val(),
			"sede": $("#sede").val(),
			"especialidad": $("#especialidad").val()
		}),
		type: 'POST',
		dataType: "json",
		contentType: 'application/json;charset=UTF-8',
		url: "/api/reporte/promedio",
		success: function(data) {
			chart3.data = data;
			chart3.update();
		}
	});
}

var data3 = [];
//Grafico linear
const options = {
	container: document.getElementById('myChart2'),
	autoSize: true,
	data: data3,
	theme: {
		palette: {
			fills: ['#5BC0EB', '#FDE74C', '#9BC53D', '#E55934', '#FA7921'],
			strokes: ['#4086a4', '#b1a235', '#6c8a2b', '#a03e24', '#af5517'],
		},
		overrides: {
			column: {
				series: {
					strokeWidth: 0,
					highlightStyle: {
						series: {
							strokeWidth: 1,
							dimOpacity: 0.3,
						},
					},
				},
			},
		},
	},
	title: {
		text: 'Promedio tiempos por usuario',
		fontSize: 18,
	},
	subtitle: {
		text: 'Promedio',
	},
	series: [
		{
			type: 'column',
			xKey: 'nombre',
			yKey: 'tiempo1',
			stacked: true,
			yName: 'Tiempo llamado',
			label: {},
		},
		{
			type: 'column',
			xKey: 'nombre',
			yKey: 'tiempo2',
			yName: 'Tiempo atencion',
			stacked: true,
			label: {},
		},
	],
	axes: [
		{
			type: 'category',
			position: 'bottom',
			label: {
				rotation: 30,
			},
		},
		{
			type: 'number',
			position: 'left',
			label: {
				formatter: (params) => {
					return params.value + 'seg';
				},
			},
		},
	],
	legend: {
		spacing: 40,
		position: 'bottom',
	},
};

var chart3 = agCharts.AgChart.create(options);

function time(f1, f2) {
	if (f2 == null)
		return 0
	else
		return (new Date(f2) - new Date(f1)) / 1000
};
