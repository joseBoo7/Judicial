function getData() {
	$.ajax({
		type: 'GET',
		url: '/api/especialidad/cantidad',
		success: function(data) {
			const options = {
				container: document.getElementById('myChart'),
				autoSize: true,
				data: data,
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
					text: 'Especialidades mas seleccionadas',
					fontSize: 18,
				},
				subtitle: {
					text: '---',
				},
				series: [
					{
						type: 'column',
						xKey: 'station',
						yKey: 'early',
						stacked: true,
						yName: 'Cantidad',
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
								return params.value +"";
							},
						},
					},
				],
				legend: {
					spacing: 40,
					position: 'bottom',
				},
			};
			

			var chart = agCharts.AgChart.create(options);
		}
	});
}
getData();