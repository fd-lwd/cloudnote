/*更改密码*/
function chanpass(){
	var lastpass=$("#last_password").val().trim();
	var newpass=$("#new_password").val().trim();
	var finalpass=$("#final_password").val().trim();
	var userId = getCookie("uid");
	//清空消息
	$("#warning_1").hide();
	$("#warning_2").hide();
	$("#warning_3").hide();
	var ok = true;//是否通过检测
	if(lastpass==""){
		$("#warning_1").show();
		ok = false;
	}
	if(newpass.length<6){
		$("#warning_2").show();
		ok = false;
	}
	if(newpass!=finalpass){
		$("#warning_3").show();
		ok = false;
	}
	if(ok){
		$.ajax({
		url:base_path+"/user/changePass.do",
		type:"post",
		data:{"userId":userId,"lastpass":lastpass,"newpass":newpass},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#back").click();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("更改密码失败");
		}
	});
		}
};