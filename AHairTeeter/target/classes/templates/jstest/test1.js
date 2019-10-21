function check(id, status) {
	$.ajax({
		type : "POST",
		data : {
			id : id,
			status : status
		},
		url : "/admin/corp/check",
		success : function(data) {
			if (data == "认证成功") {
				window.location.href = ("/admin/corp/list");
			} else {
				alert("认证失败");
			}
		},
		error : function(data) {
			alert("认证失败");
		}
	});
}