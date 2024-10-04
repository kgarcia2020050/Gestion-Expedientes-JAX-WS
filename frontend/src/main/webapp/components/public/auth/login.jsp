<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="login.css">

</head>

<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
    <h3>Ingrese sus datos</h3>

    <label for="username">Email</label>
    <input type="text" placeholder="Email" id="username" name="email" required>

    <label for="password">Contraseña</label>
    <input type="password" placeholder="Contraseña" id="password" name="password" required>

    <button type="submit">Iniciar Sesión</button>
</form>


</body>
</html>
