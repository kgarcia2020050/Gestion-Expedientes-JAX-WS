<%--
  Created by IntelliJ IDEA.
  User: KennethGarcia
  Date: 22/09/2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Expedientes</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            color: #333;
        }

        /* Estilo para la sección principal */
        .hero {
            background-color: #6c63ff;
            color: white;
            text-align: center;
            padding: 100px 20px;
            clip-path: polygon(0 0, 100% 0, 100% 85%, 0 100%);
        }

        .hero h1 {
            font-size: 3rem;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .hero p {
            font-size: 1.2rem;
            margin-bottom: 30px;
        }

        .btn-custom {
            background-color: #ff6584;
            color: white;
            padding: 15px 30px;
            font-size: 1.2rem;
            border-radius: 50px;
            transition: background-color 0.3s ease;
            display: inline-block;
            text-decoration: none;
        }

        .btn-custom:hover {
            background-color: #ff4863;
        }

        /* Estilo para la sección de características */
        .features {
            padding: 60px 20px;
        }

        .features h2 {
            font-size: 2.5rem;
            text-align: center;
            margin-bottom: 50px;
        }

        .feature-box {
            text-align: center;
            margin-bottom: 30px;
        }

        .feature-box .icon {
            display: inline-block;
            width: 80px;
            height: 80px;
            background-color: #6c63ff;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            font-size: 2rem;
            margin-bottom: 15px;
        }

        .feature-box h3 {
            font-size: 1.5rem;
            margin-bottom: 15px;
        }

        .feature-box p {
            color: #555;
        }

        /* Estilo para el pie de página */
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px 0;
        }
    </style>
</head>
<body>

<!-- Sección principal (Hero) -->
<div class="hero">
    <h1>Gestión Eficiente de Expedientes</h1>
    <p>Administra, organiza y rastrea tus expedientes desde una sola plataforma.</p>
    <a href="${pageContext.request.contextPath/login.jsp}" class="btn btn-custom">Iniciar Sesión</a>
    <a href="register.jsp" class="btn btn-custom">Registrarse</a>
</div>

<!-- Sección de características -->
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

<!-- Pie de página -->
<footer>
    <p>&copy; 2024 Gestión de Expedientes. Todos los derechos reservados.</p>
</footer>

</body>
</html>
