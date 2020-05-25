<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增员工</title>
    <script src="../../js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="../../css/generator.css">
</head>
<body>

    <div class="container">

            <div class="innerBox">
                <table class="empList" id="table2">
                    <tr>
                        <th colspan="2">员工个人信息</th>
                    </tr>
                   <tr>
                       <td>员工姓名：</td>
                       <td><input id="empName" type="text" name="name" ></td>
                   </tr>
                    <tr>
                        <td>员工邮箱：</td>
                        <td><input id="email" type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td>电话号码：</td>
                        <td><input id="phone" type="text" name="phone"></td>
                    </tr>
                    <tr>
                        <td>工资：</td>
                        <td><input id="salary" type="text" name="salary"></td>
                    </tr>
                    <tr>
                        <td>部门：</td>
                        <td>
                            <select id="dept" name="dept" style="width: 100px; height: 30px; padding: 5px;">
                                <option value="1">开发部门</option>
                                <option value="2">业务部门</option>
                                <option value="3">测试部门</option>
                                <option value="4">销售部门</option>
                            </select>
                        </td>
                    </tr>
                    <tr style="text-align: right">
                        <td></td>
                        <td>
                            <button class="button1" onclick="add()">新增</button>
                            <button class="button2" onclick="cancle()">取消</button>
                        </td>
                    </tr>
                </table>
            </div>

    </div>

    <script>

        function add() {
            let empName = document.getElementById("empName").value;
            let email = document.getElementById("email").value;
            let phone = document.getElementById("phone").value;
            let salary = document.getElementById("salary").value;
            let deptId = document.getElementById("dept").value;

            $.ajax({
                url: "/emp/insert",
                type: "POST",
                data: JSON.stringify({
                    name: empName,
                    email: email,
                    phone: phone,
                    salary: salary,
                    dept: {
                        deptId: deptId
                    }
                }),
                contentType: 'application/json',
                success: function (data) {
                    let result = data.result;
                    if(result){
                        alert("新增成功！");
                        setTimeout("javascript:location.href='showAllEmp_1.jsp'",100);
                    }else{
                        alert("新增失败！");
                    }
                },
                error: function () {
                    alert("出错了，请联系管理员！");
                }
            });
        }

        function cancle() {
            if (confirm("确定要返回原页面吗？")) {
                setTimeout("javascript:location.href='showAllEmp_1.jsp'",100);
            }
        }

    </script>
</body>
</html>
