<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/public/auth/login.css">
</head>

<body>

<div class="login-container">
    <div class="background-shape"></div>
    <h3>Iniciar Sesión</h3>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
        <div class="icon-input">
            <i class="fa-solid fa-envelope"></i>
            <input type="email" id="email" name="email" placeholder="Correo electrónico" required>
        </div>

        <div class="icon-input">
            <i class="fa-solid fa-lock"></i>
            <input type="password" id="password" name="password" placeholder="Contraseña" required>
        </div>

        <button type="submit">Iniciar Sesión</button>
    </form>
    <a href="register.jsp" class="register-link">Crear una cuenta nueva</a>
</div>

</body>
</html>
