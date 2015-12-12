<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Captcha</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div align="center">
  <form method="post" action="/registration" enctype="multipart/form-data">
    <table cellspacing="15">
        <tr>
          <td>Name</td>
          <td><input type="text" name="name" placeholder="Enter yor name"></td>
        </tr>

        <tr>
            <td>Lastname</td>
            <td><input type="text" name="lastName" placeholder="Enter yor lastname"></td>
        </tr>

        <tr>
            <td>E-mail</td>
            <td><input type="text" name="email" placeholder="Enter yor e-mail"></td>
        </tr>

        <tr>
           <td>Password</td>
           <td><input type="password" name="password" placeholder="Enter yor password"></td>
        </tr>

        <tr>
            <td>Avatar</td>
            <td><input type="file" name="file" placeholder="Attach your avatar"></td>
        </tr>

      <tr>
        <td>Are you human?</td>
        <td><input type="text" name="code" placeholder="Enter captcha"></td>
      </tr>

    </table>

    <br>
    <img src="/captcha/CaptchaServlet">

    <br><br>
    <input type="submit" value="submit">

  </form>
</div>
</body>
</html>
