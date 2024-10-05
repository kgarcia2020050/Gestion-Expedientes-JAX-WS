<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/public/auth/register.css">
</head>

<body>

<div class="register-container">
    <div class="background-shape"></div>
    <h3>Crear una cuenta nueva</h3>
    <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post">
        <div class="icon-input">
            <i class="fa-solid fa-user"></i>
            <input type="text" id="firstName" name="firstName" placeholder="Nombre" required>
        </div>

        <div class="icon-input">
            <i class="fa-solid fa-user"></i>
            <input type="text" id="lastName" name="lastName" placeholder="Apellido" required>
        </div>

        <div class="icon-input">
            <i class="fa-solid fa-envelope"></i>
            <input type="email" id="email" name="email" placeholder="Correo electrónico" required>
        </div>

        <div class="icon-input">
            <i class="fa-solid fa-lock"></i>
            <input type="password" id="password" name="password" placeholder="Contraseña" required>
        </div>

        <button type="submit">Registrar</button>
    </form>
    <a href="login.jsp" class="login-link">Iniciar sesión</a>
</div>

</body>
</html>
