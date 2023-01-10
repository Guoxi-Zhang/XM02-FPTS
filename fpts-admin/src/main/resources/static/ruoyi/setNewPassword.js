
$(function() {
    validateRule();
    $('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
});

$.validator.setDefaults({
    submitHandler: function() {
    	setNewPassword();
    }
});

function setNewPassword() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var token = $.common.trim($("input[name='token']").val());
    var password = $.common.trim($("input[name='password']").val());
    $.ajax({
        type: "post",
        url: ctx + "resetPassword/setNewPassword",
        data: {
            "token": token,
            "newPassword": password
        },
        success: function(r) {
            if (r.code == web_status.SUCCESS) {
            	layer.alert("<font color='red'>重置密码成功！</font>", {
            	    icon: 1,
            	    title: "系统提示"
            	},
            	function(index) {
            	    //关闭弹窗
            	    layer.close(index);
            	    location.href = ctx + 'login';
            	});
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#setNewPasswordForm").validate({
        rules: {
            token: {
                required: true,
                minlength: 10,
                maxlength: 10
            },
            password: {
                required: true,
                minlength: 5
            },
            confirmPassword: {
                required: true,
                equalTo: "[name='password']"
            }
        },
        messages: {
            token: {
                required: icon + "请输入令牌",
                minlength: icon + "非法的令牌",
                maxlength:  icon + "非法的令牌"
            },
            password: {
            	required: icon + "请输入您的密码",
                minlength: icon + "密码不能小于5个字符",
            },
            confirmPassword: {
                required: icon + "请再次输入您的密码",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    })
}
