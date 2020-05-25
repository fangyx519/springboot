<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工信息管理</title>
    <script src="js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="css/generator.css">
    <style type="text/css">
        h2{
            text-align: center;
        }

        .form1{
            display: inline-block;
        }

        .addButton{
            display: inline-block;
            float: right;
        }

        .innerBox{
            padding: 20px;
        }
    </style>
</head>
<body onload="showMsg()">
    <%--整个页面的div，包括form表单和table表格--%>
    <div class="container">
        <%--form表单的div--%>
        <div class="innerBox">
            <div>
                <h2>员工信息管理</h2>
            </div>

            <div class="innerBox1">

                <table class="form1" id="table1">
                    <tr>
                        <td>员工姓名：</td>
                        <td><input id="empName" type="text"></td>
                        <td>
                            <button class="button1" onclick="search()">查询</button>
                            <button class="button2" onclick="reset()">重置</button>
                        </td>
                    </tr>
                </table>

                <div class="addButton">
                    <button class="button3" onclick="javascrtpt:window.location.href='addEmp_1.jsp'">新增员工</button>
                </div>

            </div>
            <%--table表格的div--%>
            <div class="innerBox2">
                <table class="empList" id="table2">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>姓名</th>
                            <th>邮箱</th>
                            <th>电话号码</th>
                            <th>工资</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                </table>
            </div>

        </div>
    </div>

    <script>

        function showMsg() {
            $.ajax({
                url: "emp/showAllEmp",
                type: "GET",
                contentType: 'application/json',
                success: function (data) {
                    showData(data);
                },
                error: function () {
                    alert("出错了，请联系管理员！");
                }
            });
        }

        function showData(data) {
            let i,str,url;
            let len = data.length;
            for (i = 0;i < len; i++){
                str = "<tbody><tr><td>"+data[i].id+"</td><td>"
                +data[i].name+"</td><td>"
                +data[i].email+"</td><td>"
                +data[i].phone+"</td><td>"
                +data[i].salary+"</td><td>"
                +data[i].dept.deptName+"</td><td>"
                +"<a href='javascript:void(0)' onclick='modifyEmp("+data[i].id+")'>修改</a>"+"&nbsp;"
                +"<a href='javascript:void(0)' onclick='deleteEmp("+data[i].id+")'>删除</a></td><tr><tbody>";
                $("#table2").append(str);
            }
        }

        function modifyEmp(data){
            window.location.href = 'modifyEmp_1.jsp?id='+data;
        }

        function deleteEmp(data) {
            if(confirm("确定要删除该员工相关信息吗？")){
                $.ajax({
                    url: "/emp/delete/"+data,
                    type: "DELETE",
                    contentType: 'application/json',
                    success: function (text) {
                        let result = text.result;
                        if(result){
                            alert("删除成功！");
                            setTimeout("javascript:location.href='showAllEmp_1.jsp'",100);
                        }else{
                            alert("删除失败！");
                        }
                    },
                    error: function () {
                        alert("出错了，请联系管理员！");
                    }
                });
            }

        }

        // 查询按钮
        function search() {
            let data = document.getElementById("empName").value;
            $.ajax({
                url: "/emp/queryByName/"+data,
                type: "GET",
                contentType: 'application/json',
                success: function (data) {
                    $("#table2 tbody").html("");
                    showData(data);
                },
                error: function () {
                    alert("出错了，请联系管理员！");
                }
            });
        }

        // 重置按钮
        function reset() {
            $("#empName").val("");
            $("#table2 tbody").html("");
            showMsg();
        }

        /*function getCookie(cname) {
            let name = cname + "=";
            let decodedCookie = decodeURIComponent(document.cookie);
            let ca = decodedCookie.split(';');
            for(let i = 0; i < ca.length; i++) {
                let c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        }

        function checkCookie() {
            let user = getCookie("username");
            if (user != "") {
               showMsg();
            } else {
                window.location.href = 'login_1.jsp';
            }
        }*/
    </script>
</body>
</html>
