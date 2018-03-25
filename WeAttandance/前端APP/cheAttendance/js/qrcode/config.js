 //通用接口地址设置  
 //  183.26.245.185
var server_url = "http://172.16.41.183:8080/";  
var tokenHead="ttkoenn "
function getServerUrl(){  
  return server_url;  
}  
function getAuthorization(){
//	mui.plusReady(function(){
		var authorization=tokenHead+plus.storage.getItem("authorization");
		console.log("authorization:"+authorization);
	//	var test="ttkoenn eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGVhcmxvdmU3IiwiY3JlYXRlZCI6MTUxMjU3MDk5MjQxOCwiZXhwIjo2MDAwMTUxMjU3MDk5Mn0.y6bwLRS_xQtqcVdrrrEtaUT3B5y1yICJR6rD5BcHrKDsKGJOTY575JCFbrkaOOh-fmL1QCpZmucjRdhQCxu2QA";
		return authorization;
//	});
}
function getUserId(){
//	mui.plusReady(function(){ 
		var userData=plus.storage.getItem("userData");
		var userId= JSON.parse(userData).pkUserId;
//		var test=1;
		console.log("userId:"+userId);
		return userId;
//	});
} 

/**
 *  ajax get 请求 带有authorization
 * 
 */
function ajaxGetAuthorization(path,dataJson,callSucessful,callFail){
	 	var serveUrl=getServerUrl()+path;
	// 	var authorization="ttkoenn eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYWEiLCJjcmVhdGVkIjoxNTEyNjQ0NzMwMjU5LCJleHAiOjYwMDAxNTEyNjQ0NzMwfQ.a--xlB5f-BuhJ9aVzGv2mC5fmpkC6SvIhGY6_OuRKbMrP9h1zvw5mqVZywyRqEN4PTKZiyZTkurZnsZT8GSgAQ";
		var authorization=getAuthorization();
		
		mui.ajax(serveUrl,{
	  		data: dataJson,
			dataType:'json',//服务器返回json格式数据   text 文本类型
			type:'get',//HTTP请求类型
		//	async:false,
			timeout:10000,//超时时间设置为10秒；
			headers:{
	      			'content-type': 'application/json; charset=utf-8',
	      			"Authorization": authorization
	    		},  
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				console.log("Sucessful");
				callSucessful(data);
				  
			},
			error:function(xhr,type,errorThrown){ 
				console.log("Fail");
				callFail(type);
			}  
		});  
}
/**
 *  ajax post 请求 带有authorization
 * 
 */
function ajaxPostAuthorization(path,dataJson,callSucessful,callFail){
	 	var serveUrl=getServerUrl()+path;
	// 	var authorization="ttkoenn eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGVhcmxvdmU3IiwiY3JlYXRlZCI6MTUxMjIxOTA0NTc0OSwiZXhwIjo2MDAwMTUxMjIxOTA0NX0.V5X7EHqtQQH1-7S9I9KfWHzRA3fF9rdGeUNJ1QP6W8_0QPTTok74XxlQ4ccDXpULwoExHctbUGf7YBeGN3cy0A";
		var authorization=getAuthorization();
		console.log(authorization);
	//	var strData="{\"attendId\":1}";
	//	var dataJson= JSON.parse(strData);
	//	console.log(JSON.stringify(dataJson));
		mui.ajax(serveUrl,{
	  		data: dataJson,
			dataType:'json',//服务器返回json格式数据   text 文本类型
			type:'post',//HTTP请求类型
		//	async:false,
			timeout:10000,//超时时间设置为10秒；
			headers:{
	      		//	'content-type': 'application/json; charset=utf-8',
	      		    'contentType' : "application/x-www-form-urlencoded",
	      			"Authorization": authorization
	    		},  
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				console.log("Sucessful");
				callSucessful(data);
				
			},
			error:function(xhr,type,errorThrown){ 
				console.log("Fail");
				callFail(type);
			}  
		});  
}

/**
 *  ajax post 请求
 * 
 */
function ajaxPost(path,dataJson,callSucessful,callFail){
	 	var serveUrl=getServerUrl()+path;
		mui.ajax(serveUrl,{
	  		data: dataJson,
			dataType:'json',//服务器返回json格式数据   text 文本类型
			type:'post',//HTTP请求类型
		//	async:false,
			timeout:10000,//超时时间设置为10秒；
			header:{
	      			'content-type': 'application/json; charset=utf-8'
	    		},  
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				console.log("Sucessful");
				callSucessful(data);
				
			},
			error:function(xhr,type,errorThrown){ 
				console.log("Fail");
				callFail(type);
			}  
		});  
}
/**
 *  ajax get 请求
 * 
 */
function ajaxGet(path,dataJson,callSucessful,callFail){
	 	var serveUrl=getServerUrl()+path;
		mui.ajax(serveUrl,{
	  		data: dataJson,
			dataType:'json',//服务器返回json格式数据   text 文本类型
			type:'get',//HTTP请求类型
		//	async:false,
			timeout:10000,//超时时间设置为10秒；
			header:{
	      			'content-type': 'application/json; charset=utf-8'
	    		},  
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				console.log("Sucessful");
				callSucessful(data);
				
			},
			error:function(xhr,type,errorThrown){ 
				console.log("Fail");
				callFail(type);
			}  
		});  
}

