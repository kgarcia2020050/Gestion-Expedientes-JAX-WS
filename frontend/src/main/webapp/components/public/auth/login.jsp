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
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/public/auth/login.css">
</head>

<body>

<div class="login-container">
    <div class="background-shape"></div>
    <h3>Iniciar Sesión</h3>


    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
        <div class="icon-input">
            <i class="fa-solid fa-envelope"></i>
            <input type="email" id="email" name="email" placeholder="Correo electrónico" required>
        </div>

        <div class="icon-input">
            <i class="fa-solid fa-lock"></i>
            <input type="password" id="password" name="password" placeholder="Contraseña" required>
        </div>

        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <button type="submit">Iniciar Sesión</button>
    </form>
    <a href="${pageContext.request.contextPath}/components/public/auth/register.jsp" class="register-link">Crear una cuenta nueva</a>
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
