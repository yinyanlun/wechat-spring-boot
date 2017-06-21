  $(document).ready(function() {
    	// 对账单详细信息
		$billDetailResultTable = $('#datatable-responsive');
		$billDetailResultTable.bootstrapTable({
			method: 'post',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",//必须的，操你大爷！！！！
			url : "/yin/userInfo/pageUser",
			dataType : "json",
			showColumns : false,
			showRefresh : false,
			pagination : true,//分页
			singleSelect : true,
			clickToSelect : true,
			search : true, //显示搜索框
			sidePagination : "server", //服务端处理分页
			striped : true, // 隔行加亮
			pageSize : 15,
			queryParams: queryDetailParams,
			minimunCountColumns: 2,
			searchText:"请输入用户名查询",
			pageList:[15,20],
			columns : [ { // 列设置
				field : '',
				checkbox : true
			// 使用复选框
			}, {
				title : 'ID',
				field : 'uid',
				align : 'left',
				valign : 'middle',
				sortable : false
			// 开启排序功能
			}, {
				title : '收费项目',
				field : 'cpName',
				align : 'left',
				valign : 'middle',
				sortable : false
			// 开启排序功能
			}, {
				title : '姓名',
				field : 'payuser',
				align : 'center',
				valign : 'middle',
				sortable : false
			// 开启排序功能
			}, {
				title : '金额/元',
				field : 'paymoney',
				align : 'center',
				valign : 'middle',
			}, {
				title : '操作时间',
				field : 'paytime',
				align : 'center',
				valign : 'middle',
			    formatter : dataFormatter
			},{
				title : '操作人',
				field : 'userName',
				align : 'center',
				valign : 'middle'
			   
			},{
				title : '订单号',
				field : 'tradeno',
				align : 'center',
				valign : 'middle',
			},{
				title : '支付状态',
				field : 'state',
				align : 'center',
				valign : 'middle',
				formatter : startFormatter
			},{
				title : '操作',
				field : 'state',
				align : 'center',
				valign : 'middle',
				formatter : StringFormatter
			}]
		});
		function dataFormatter (value, row, index){
			var date = new Date(value);  
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
		    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;   
			
		}
		
		function startFormatter(value, row, index){
			var startStr;
			if(value==0){
				startStr="<button type='button' class='btn btn-success btn-xs'>已支付</button>";
			}
			else if(value==1){
				startStr="<button type='button' class='btn btn-warning btn-xs'>未支付</button>";
			}
			else{
				startStr="<button type='button' class='btn btn-danger btn-xs'>已退款</button>";
			}
			return startStr;
		}
		function StringFormatter(value, row, index) {
			if(value==0){
				return '<a href="#" onclick="reverseOrder('+row.id+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i> 撤销 </a>'
			}
			else if(value==1){
				startStr="未支付";
			}
			else{
				startStr="已撤销";
			}
			
		}
		
		
		// 传递参数
		function queryDetailParams(params) {
			opt = {
			        url: "userinfo/list",
			        silent: true,
			        query:{
						limit:params.limit,
						offset:params.offset,
						search:params.search,
					}
			    };
			return {
				limit:params.limit,
				offset:params.offset,
				search:params.search,
			}

		}
		
    });