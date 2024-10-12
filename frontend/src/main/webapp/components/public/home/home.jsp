<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Expedientes</title>
    <%@ include file="../../../layout/header.jsp" %>
    <link href="${pageContext.request.contextPath}/components/public/home/home.css" rel="stylesheet">
</head>
<body>

<div class="hero">
    <h1>Gestión Eficiente de Expedientes</h1>
    <p>Administra, organiza y rastrea tus expedientes desde una sola plataforma.</p>
    <a href="${pageContext.request.contextPath}/components/public/auth/login.jsp" class="btn btn-custom">Iniciar Sesión</a>
    <a href="${pageContext.request.contextPath}/components/public/auth/register.jsp" class="btn btn-custom">Registrarse</a>
</div>

<div class="features container">
    <h2>Nuestras Funcionalidades</h2>
    <div class="row">
        <div class="col-md-4 feature-box">
            <div class="icon">&#128202;</div>
            <h3>Seguimiento en Tiempo Real</h3>
            <p>Obtén una visión completa del progreso de cada expediente y su estado en tiempo real.</p>
        </div>
        <div class="col-md-4 feature-box">
            <div class="icon">&#128218;</div>
            <h3>Organización Eficiente</h3>
            <p>Mantén todos tus expedientes bien organizados y accesibles desde cualquier lugar.</p>
        </div>
        <div class="col-md-4 feature-box">
            <div class="icon">&#128101;</div>
            <h3>Colaboración de Equipos</h3>
            <p>Trabaja junto a tu equipo de forma colaborativa para gestionar expedientes de manera eficiente.</p>
        </div>
    </div>
</div>

<footer>
    <p>&copy; 2024 Gestión de Expedientes. Todos los derechos reservados.</p>
</footer>
</body>
</html>
