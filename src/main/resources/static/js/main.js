   $(document).ready(function(){
    	$.ajax({
		    url:"dashboard",
		    type:'post', //GET
		    async:true,    //或false,是否异步
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    	
		    },
		    success:function(data,textStatus,jqXHR){
		    	   console.log(data);
		    	   $("#alipayCount").html(data.alipayCount);
		    	   $("#alipaySum").html(data.alipaySum);
		    	   $("#weixinpayCount").html(data.weixinpayCount);
		    	   $("#weixinpaySum").html(data.weixinpaySum);
		    	   $('#container').highcharts({
		    	        chart: {
		    	            type: 'area'
		    	        },
		    	        credits: { 
	    	            	enabled: false //不显示LOGO 
	    	            },
		    	        title: {
		    	            text: '最近七天微信支付宝交易统计'
		    	        },
		    	        subtitle: {
		    	            text: '-交易成功总额:单位/元-'
		    	        },
		    	        xAxis: {
		    	            categories: data.dateArrat,
		    	            tickmarkPlacement: 'on',
		    	            title: {
		    	                enabled: false
		    	            }
		    	        },
		    	        yAxis: {
		    	            title: {
		    	                text: '元'
		    	            },
		    	            labels: {
		    	                formatter: function () {
		    	                    return this.value ;
		    	                }
		    	            }
		    	        },
		    	        tooltip: {
		    	            split: true,
		    	            valueSuffix: '元'
		    	        },
		    	        plotOptions: {
		    	            area: {
		    	                stacking: 'normal',
		    	                lineColor: '#666666',
		    	                lineWidth: 1,
		    	                marker: {
		    	                    lineWidth: 1,
		    	                    lineColor: '#666666'
		    	                }
		    	            }
		    	        },
		    	        series: [{
		    	            name: '支付宝',
		    	            data: data.alipayArray
		    	        }, {
		    	            name: '微信',
		    	            data: data.weixinpayArray
		    	        }]
		    	    });
		    	   $('#container2').highcharts({
		    	        chart: {
		    	            type: 'column',
		    	          
		    	        },
		    	        credits: { 
	    	            	enabled: false //不显示LOGO 
	    	            },
		    	        title: {
		    	            text: data.searchData+ '各收费员收费统计'
		    	        },
		    	        subtitle: {
		    	            text: '-微信 支付宝成功交易统计(笔数/总额)-'
		    	        },
		    	        xAxis: {
		    	            categories: data.userNameList,
		    	            crosshair: true
		    	        },
		    	        yAxis: {
		    	            min: 0,
		    	            title: {
		    	                text: '笔数/笔 金额/元'
		    	            }
		    	        },
		    	        tooltip: {
		    	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		    	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		    	            '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
		    	            footerFormat: '</table>',
		    	            shared: true,
		    	            useHTML: true
		    	        },
		    	        plotOptions: {
		    	            column: {
		    	                pointPadding: 0.2,
		    	                borderWidth: 0
		    	            }
		    	        },
		    	        series: [{
		    	            name: '微信笔数',
		    	            data: data.user_weixinPayCountArray
		    	        }, {
		    	            name: '微信总额',
		    	            data: data.user_weixinPaySumArray
		    	        }, {
		    	            name: '支付宝笔数',
		    	            data: data.user_aliPayCountArray
		    	        }, {
		    	            name: '支付宝总额',
		    	            data: data.user_aliPaySumArray
		    	        }]
		    	    });
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		});
    	$("#single_cal1").change(function(){
    		var tateStr=($("#single_cal1").val());
    		var date = new Date(tateStr);  
		    var y = date.getFullYear();    
		    var m = date.getMonth() + 1;    
		    m = m < 10 ? ('0' + m) : m;    
		    var d = date.getDate();    
		    d = d < 10 ? ('0' + d) : d;    
		    var h = date.getHours();  
		    h = h < 10 ? ('0' + h) : h;  
		    var minute = date.getMinutes();  
		    var second = date.getSeconds();  
		    minute = minute < 10 ? ('0' + minute) : minute;    
		    second = second < 10 ? ('0' + second) : second;   
		    tateStr= y + '-' + m + '-' + d;
    		$.ajax({
			    url:"dashboard/searchByDate?",
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
			    	date:tateStr
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			    },
			    success:function(data,textStatus,jqXHR){
			    	if(data.DATA_CODE=="SUCCESS")
			    		{
			    		$('#container2').highcharts({
			    	        chart: {
			    	            type: 'column',
			    	          
			    	        },
			    	        credits: { 
		    	            	enabled: false //不显示LOGO 
		    	            },
			    	        title: {
			    	            text: data.searchData+ '各收费员收费统计'
			    	        },
			    	        subtitle: {
			    	            text: '-微信 支付宝成功交易统计(笔数/总额)-'
			    	        },
			    	        xAxis: {
			    	            categories: data.userNameList,
			    	            crosshair: true
			    	        },
			    	        yAxis: {
			    	            min: 0,
			    	            title: {
			    	                text: '笔数/笔 金额/元'
			    	            }
			    	        },
			    	        tooltip: {
			    	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			    	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			    	            '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
			    	            footerFormat: '</table>',
			    	            shared: true,
			    	            useHTML: true
			    	        },
			    	        plotOptions: {
			    	            column: {
			    	                pointPadding: 0.2,
			    	                borderWidth: 0
			    	            }
			    	        },
			    	        series: [{
			    	            name: '微信笔数',
			    	            data: data.user_weixinPayCountArray
			    	        }, {
			    	            name: '微信总额',
			    	            data: data.user_weixinPaySumArray
			    	        }, {
			    	            name: '支付宝笔数',
			    	            data: data.user_aliPayCountArray
			    	        }, {
			    	            name: '支付宝总额',
			    	            data: data.user_aliPaySumArray
			    	        }]
			    	    });
			    		}
			    	else{
			    		
			    	}
			
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			});
    	})
    	});