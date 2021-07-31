$(function() {

    Morris.Line({
        element: 'morris-one-line-chart',
            data: [
                { month: '6', value: 50000 },
                { month: '7', value: 70000 },
                { month: '8', value: 90000 },
                { month: '9', value: 120000 },
                { month: '10', value: 200000 },
                {month: '11', value: 300000 },
                { month: '12', value: 500000 }
            ],
        xkey: 'month',
        ykeys: ['value'],
        resize: true,
        lineWidth:4,
        labels: ['Value'],
        lineColors: ['#1ab394'],
        pointSize:5,
    });

    Morris.Area({
        element: 'morris-area-chart',
        data: [{ period: '2020 Q1', a: 29, b: 26, c: 25 ,d: 30, e: 23},
            { period: '2020 Q2', a: 10, b: 18, c: 17 ,d: 13, e: 14 },
            { period: '2020 Q3', a: 32, b: 29, c: 29 ,d: 36, e: 25 },
            { period: '2020 Q4', a: 34, b: 30, c: 29 ,d: 39, e: 26.5 },
            { period: '2021 Q1', a: 35, b: 31, c: 29 ,d: 40, e: 28 },
            { period: '2021 Q2', a: 36, b: 32, c: 30 ,d: 41.5, e: 29 },
            { period: '2021 Q3', a: 38, b: 33, c: 31 ,d: 42, e: 30 },
            { period: '2021 Q4', a: 39, b: 34, c: 32 ,d: 43, e: 31 },
            { period: '2022 Q1',a: 40, b: 35, c: 33 ,d: 44, e: 32 },
            { period: '2022 Q2', a: 42, b: 36, c: 35 ,d: 45, e: 33 } ],
        xkey: 'period',
        ykeys: ['a', 'b', 'c','d','e'],
        labels: ['新鲜水果', '海鲜水产', '精选肉类','冷饮冻食','蔬菜蛋品'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true,
        lineColors: ['#54cdb4','#1ab394','#cacaca','#baeae2','#1ab698'],
        lineWidth:2,
        pointSize:1,
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{ label: "新鲜水果", value: 15 },
            { label: "海鲜水产", value: 30 },
            { label: "精选肉类", value: 20 },
            { label: "冷饮冻食", value: 10 },
            { label: "蔬菜蛋品", value: 25 }],
        resize: true,
        colors: ['#54cdb4','#1ab394','#cacaca','#baeae2','#1ab698'],
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{ y: '6', a: 60 },
            { y: '7', a: 95},
            { y: '8', a: 180 },
            { y: '9', a: 195 },
            { y: '10', a: 215 },
            { y: '11', a: 255},
            { y: '12', a: 320 } ],
        xkey: 'y',
        ykeys: ['a'],
        labels: ['产品销量'],
        hideHover: 'auto',
        resize: 15,
        barColors: ['#1ab394', '#cacaca'],
    });

    Morris.Line({
        element: 'morris-line-chart',
        data: [{ y: '0', a: 100, b: 90 ,c:80, d:75 ,e: 70},
            { y: '1',  a: 95, b: 95 ,c:85, d:80 ,e: 73 },
            { y: '2',  a: 98, b: 92 ,c:82, d:79 ,e: 85 },
            { y: '3',  a: 112, b: 103 ,c:108, d:88 ,e: 92 },
            { y: '4',  a: 125, b: 103 ,c:102, d:112 ,e: 105 },
            { y: '5',  a: 136, b: 118 ,c:126, d:103 ,e: 100},
            { y: '6',  a: 143, b: 110 ,c:105, d:105 ,e: 99 } ],
        xkey: 'y',
        ykeys: ['a', 'b','c','d','e'],
        labels: ['新鲜水果', '海鲜水产','精选肉类','冷饮冻食','蔬菜蛋品'],
        hideHover: 'auto',
        resize: true,
        lineColors: ['#54cdb4','#1ab394','#cacaca','#baeae2','#1ab698'],
    });

});
