output_chart();
function output_chart() {
	var ctx = document.getElementById("myChart").getContext("2d");
	
	new Chart(ctx, {
    type: 'line',
    data: {
        labels: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        datasets: [{
            label: '月別支出金額',
            data: [80000, 39000, 30000, 50000, 20000, 30000, 15000, 60000, 45000, 38000, 29000, 58000],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
		options: {
			layout: {
				padding: {
					left: 300,
					right: 0,
					top: 50,
					bottom: 150
				}
			},
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			}
		}
});
}