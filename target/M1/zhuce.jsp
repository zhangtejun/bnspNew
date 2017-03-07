<%--
  Created by IntelliJ IDEA.
  User: zhangchi
  Date: 16/11/10
  Time: 上午9:14
  To change this template use File | Settings | File Templates.
--%>
< page contentType="text/html;charset=UTF-8" language="java" >
<html>
<head>
    <title>注册</title>

</head>

<body>

<form name="from2" action="user/register.do" method="post">
    <table width="300" border="1">

        <tr>
            <td colspan="2"> 注册窗口</td>
        </tr>
        <tr>
            <td > 用户名</td>
            <td > <input type="text" name="username" size="10" required></td>
        </tr>

        <tr>
            <td > 密码</td>
            <td > <input type="password" name="password" size="10"></td>
        </tr>

        <tr>
            <td colspan ="2"> <input type="submit" name="submit" value="注册">
        </tr>

    </table>

</form>

</body>
</html>
