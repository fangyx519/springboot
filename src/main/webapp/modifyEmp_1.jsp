<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改员工信息</title>
    <script src="js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="css/generator.css">
</head>
<body onload="getEmpInfo()">

    <div class="container">

            <div class="innerBox">
                <table class="empList" id="table2">
                    <tr>
                        <th colspan="2">员工个人信息</th>
                    </tr>
                    <tr>
                        <td>员工编号：</td>
                        <td><input id="empId" type="text" name="id" readonly="true"></td>
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
                        <td><input id="salary" type="text" name="salary" readonly="true"></td>
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
                            <button class="button1" onclick="modify()">修改</button>
                            <button class="button2" onclick="cancle()">取消</button>
                        </td>
                    </tr>
                </table>
            </div>

    </div>

    <script>
        function getEmpInfo() {
            $.ajax({
                url: "/emp/"+getQueryString("id"),
                type: "GET",
                contentType: 'application/json',
                success: function (data) {
                    document.getElementById("empId").value = data.id;
                    document.getElementById("empName").value = data.name;
                    document.getElementById("email").value = data.email;
                    document.getElementById("phone").value = data.phone;
                    document.getElementById("salary").value = data.salary;
                    document.getElementById("dept").value = data.dept.deptId;
                },
                error: function () {}
            });
        }

        function modify() {
            let empId = document.getElementById("empId").value;
            let empName = document.getElementById("empName").value;
            let email = document.getElementById("email").value;
            let phone = document.getElementById("phone").value;
            let salary = document.getElementById("salary").value;
            let deptId = document.getElementById("dept").value;

            $.ajax({
                url: "/emp/update",
                type: "PUT",
                data: JSON.stringify({
                    id: empId,
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
                        alert("修改成功！");
                        setTimeout("javascript:location.href='showAllEmp_1.jsp'",100);
                    }else{
                        alert("修改失败！");
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
        
        function getQueryString(name) {
            let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            let r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

    </script>
</body>
</html>
