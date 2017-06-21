 var opt={}; 
$(document).ready(function() {
    	// 对账单详细信息
		$billDetailResultTable = $('#datatable-responsive');
		$billDetailResultTable.bootstrapTable({
			method: 'post',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",//必须的，操你大爷！！！！
			url : "/yin/weixinUserInfo/page",
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
			searchText:"昵称或OEENID查询",
			pageList:[15,20],
			columns : [ {
				title : '序号',
				align : 'center',
				valign : 'middle',
				sortable : false,
				 formatter: function (value, row, index) {  
                     return index+1;  
                 }  
			// 开启排序功能
			},  {
				title : 'ID',
				field : 'id',
				align : 'center',
				valign : 'middle',
				sortable : false
			// 开启排序功能
			}, {
				title : '头像',
				field : 'headImgUrl',
				align : 'center',
				valign : 'middle',
				sortable : false,
				formatter: function(value,row,index){
                    return '<img  src="'+value+'" class="table-img" >';
                }
			// 开启排序功能
			},{
				title : '昵称',
				field : 'nickname',
				align : 'center',
				valign : 'middle'
			   
			}, {
				title : 'OPPENID',
				field : 'openId',
				align : 'center',
				valign : 'middle',
				sortable : true
			// 开启排序功能
			}, {
				title : '性别',
				field : 'sex',
				align : 'center',
				valign : 'middle',
				formatter: function(value,row,index){
					if(value=="1"){
						return "<button type='button' class='btn btn-danger btn-xs'>男</button>";
					}else if(value=="0"){
						return "<button type='button' class='btn btn-success btn-xs'>女</button>";
					}else{
						return '未知';
					}
						
                    
                }
			}, {
				title : '关注时间',
				field : 'subscribeDate',
				align : 'center',
				valign : 'middle',
			    formatter : dataFormatter
			},{
				title : '关注状态',
				field : 'subscribe',
				align : 'center',
				valign : 'middle',
				formatter: function(value,row,index){
					var startStr;
					if(value==1){
						startStr="<button type='button' class='btn btn-success btn-xs'>已关注</button>";
					}
					else{
						startStr="<button type='button' class='btn btn-danger btn-xs'>未关注</button>";
					}
					return startStr;
                    
                }
			},{
				title : '使用状态',
				field : 'state',
				align : 'center',
				valign : 'middle',
				formatter: function(value,row,index){
					var startStr;
					if(value==1){
						startStr="<button type='button' class='btn btn-success btn-xs'>可以使用</button>";
					}
					else{
						startStr="<button type='button' class='btn btn-danger btn-xs'>禁止使用</button>";
					}
					return startStr;
                    
                }
			},{
				title : '同步微信',
				field : 'id',
				align : 'center',
				valign : 'middle',
				formatter: function(value,row,index){
					return '<a href="#" onclick="refresh('+value+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i>同步</a>';
                    
                }
			},{
				title : '操作',
				field : 'state',
				align : 'center',
				valign : 'middle',
				formatter: function(value,row,index){
					var startStr;
					if(value==1){
						
						startStr='<a href="#" onclick="updateState('+row.id+','+value+')" class="btn btn-danger btn-xs"><i class="fa fa-pencil"></i>禁止使用</a>';
					}
					else{
						startStr='<a href="#" onclick="updateState('+row.id+','+value+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i>开启使用</a>';
					}
					return startStr;
                    
                }
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
		// 传递参数
		function queryDetailParams(params) {
			opt = {
			        url: "/yin/weixinUserInfo/page",
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
  //拉黑取消拉黑操作
  function updateState(id,value){
		var state=0;
		(value == 1) ? state=0 : state=1;
		$.ajax({
		    url:"/yin/weixinUserInfo/updateStateById",
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	id:id,
		    	state:state
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    },
		    success:function(data,textStatus,jqXHR){
		        console.log(data);
		        if(data.DATA_CODE=="SUCCESS")
		        	{
		        	$billDetailResultTable.bootstrapTable('refresh',opt); 
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
		})
	};
	function refresh(id){
		$.ajax({
		    url:"/yin/weixinUserInfo/refresh",
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	id:id
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    },
		    success:function(data,textStatus,jqXHR){
		        console.log(data);
		        	$billDetailResultTable.bootstrapTable('refresh',opt); 
		        	
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
	};
	