<%--
  Created by IntelliJ IDEA.
  User: fyx
  Date: 2020/5/5
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.5.1.js"></script>
    <script src="js/script.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="container">
        <div class="loginBox">

            <form id="loginForm">
                <div class="input-wrapper">
                    <label>用户名：</label>
                    <input id="name" type="text" name="mgrname" placeholder="请输入用户名">
                    <div class="error-name"><div class="name-msg"></div><div class="triangle"></div></div>
                </div>

                <div class="input-wrapper">
                    <label>密码：</label>
                    <input id="pwd" type="password" name="mgrpwd" placeholder="密码">
                    <div class="error-pwd"><div class="pwd-msg"></div><div class="triangle"></div></div>
                </div>
                <input class="registBtn" type="button" name="" value="注册" onclick="login()">
                <div class="border">
                    <a class="toLogin" href="login_1.jsp">已有账号，去登陆！</a>
                </div>
            </form>

        </div>
    </div>

    <div id="showMsg"></div>
</body>

<script type="text/javascript">
    $(function () {
        $(".error-name").hide();
        $(".error-pwd").hide();
    })

    function login() {
        var flag = true;
        var nameInput = $("#name");
        var pwdInput = $("#pwd");
        if(nameInput.val() == ""){
            $(".error-name").fadeIn();
            $(".name-msg").html("用户名不能为空");
            flag = false;
        }
        if(pwdInput.val().length < 5){
            $(".error-pwd").fadeIn();
            $(".pwd-msg").html("密码必须至少有5个字符");
            flag = false;
        }
        setTimeout(function showErrorMsg() {
            $(".error-name, .error-pwd").fadeOut();
        },2000)

        if(flag){
            $.ajax({
                url : "/mgr/register",
                type : "GET",
                data : JSON.stringify({
                    mgrname : "12",
                    mgrpwd : "123456",
                }),
                contentType: 'application/json',
                dataType : "json",
                success : function (data) {
                    console.log(data)
                    $("#showMsg").append("<p>数据增加成功：" + data + "</p>");
                },
                error : function (d,error) {
                    console.log(error)
                }
            });
        }
    }
</script>

</html>
