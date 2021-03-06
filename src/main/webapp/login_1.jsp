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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script src="js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="container">
        <div class="loginBox">

            <%--做一个注册登录切换的Tab页--%>
            <div class="userImage">
                <img src="img/catFace.png">
            </div>
            <form id="form1">
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
                <input class="loginBtn" type="button" name="" value="登录" onclick="login()">

                <div class="border">
                    <a class="toRegist" href="regist_1.jsp">注册账号！</a>
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
        var name = $("#name").val();
        var pwd = $("#pwd").val();
        if(name == ""){
            $(".error-name").fadeIn();
            $(".name-msg").html("用户名不能为空");
            flag = false;
        }
        if(pwd.length < 5){
            $(".error-pwd").fadeIn();
            $(".pwd-msg").html("密码必须至少有5个字符");
            flag = false;
        }
        setTimeout(function showErrorMsg() {
            $(".error-name, .error-pwd").fadeOut();
        },2000)

        if(flag){
            $.ajax({
                url : "mgr/login",
                type : "POST",
                data : JSON.stringify({
                    mgrname : name,
                    mgrpwd : pwd,
                }),
                contentType: 'application/json',
                dataType : "json",
                success : function (text) {
                    console.log(text);
                    var result = text.result;
                    if (result){
                        //setCookie("username", name, 0.5);
                        setTimeout("javascript:location.href='showAllEmp_1.jsp'",100);
                    }else{
                        alert("用户名或密码错误！");
                    }

                },
                error : function () {
                    alert("出错了，请联系管理员!");
                }
            });
        }


    }

    /*function setCookie(cname,cvalue,exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }*/
</script>

</html>
