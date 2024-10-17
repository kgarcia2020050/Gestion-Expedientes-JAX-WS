<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String errorMessage = "";
    String successMessage = "";
    if (session.getAttribute("error") != null) {
        errorMessage = (String) session.getAttribute("error");
        session.removeAttribute("error");
    }

    if (session.getAttribute("success") != null) {
        successMessage = (String) session.getAttribute("success");
        session.removeAttribute("success");
    }

%>

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
    <a href="${pageContext.request.contextPath}/components/public/auth/login.jsp" class="login-link">Iniciar sesión</a>
</div>

</body>
<%@ include file="../../../layout/footer.jsp" %>
<script>
    const errorMessage = "<%= errorMessage %>";
    const successMessage = "<%= successMessage %>";
    if (errorMessage.length > 0) {
        console.log("ENTRA")
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: errorMessage
        });
    }

    if (successMessage.length > 0) {
        Swal.fire({
            icon: 'success',
            title: '¡Éxito!',
            text: successMessage
        });
    }

</script>
</html>
