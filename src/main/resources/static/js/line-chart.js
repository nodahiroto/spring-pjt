output_chart();
function output_chart() {
//	var ctx = document.getElementById("myChart").getContext("2d");
	const $myChart = document.querySelector('#myChart');
	const ctx = $myChart.getContext('2d');
	
	const january = $myChart.dataset.January;
	const february = $myChart.dataset.February;
	const march = $myChart.dataset.March;
	const april = $myChart.dataset.April;
	const may = $myChart.dataset.May;
	const june = $myChart.dataset.June;
	const july = $myChart.dataset.July;
	const august = $myChart.dataset.August;
	const september = $myChart.dataset.September;
	const october = $myChart.dataset.October;
	const november = $myChart.dataset.November;
	const december = $myChart.dataset.December;
	
	new Chart(ctx, {
    type: 'line',
    data: {
        labels: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        datasets: [{
            label: '月別支出金額',
            data: [january, february, march, april, may, june, july, august, september, october, november, december],
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