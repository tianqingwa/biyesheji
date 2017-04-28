// 延时加载验证码
function load_validateCode(){
	$("#img_container").html('<img src="/servlet/jcaptcha?" id="vcode" style="cursor:pointer;" onclick="this.src=this.src+\'?\'" alt="看不清?请点击刷新!" title="看不清?请点击刷新!" />');
}
		
$(function(){
	$("#submitForm").validate({
		submitHandler:function(form){
			// 按钮灰化
			disabledBtn();
			// 表单提交
			$.post("/login.sec", $(form).serialize(), function(data){
				if(data == "success")
					top.location.href = "/static/html/server/index.html";
				else{
					alert(data);
					undisabledBtn();
					$("#validateCode").val("");
					$("#vcode").attr("src", function(){
						return this.src + "?";
					});
				}
			});
			return false;
		}
	});
});