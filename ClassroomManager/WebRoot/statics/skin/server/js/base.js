// 加载头部文件
$(function() {
	$("#head").load("/static/html/server/head.html");
});

// 灰化按钮
function disabledBtn() {
	$("#submitBtn").attr("disabled", "disabled").text("loading...");
	$("#resetBtn").attr("disabled", "disabled");
}

// 启用按钮
function undisabledBtn() {
	$("#submitBtn").removeAttr("disabled").text("提交");
	$("#resetBtn").removeAttr("disabled");
}