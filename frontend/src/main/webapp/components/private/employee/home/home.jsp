<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/private/employee/home/home.css">
</head>
<body>

<div class="main-content">
    <div class="content-container">
        <div class="welcome-section">
            <h1>Bienvenido al Departamento</h1>
            <p>Aquí puedes gestionar y aprobar expedientes</p>
        </div>

        <div class="main-content">
            <div class="card">
                <h3>Expedientes en Proceso</h3>
                <p>Revisa los expedientes que están en espera de ser aprobados o revisados.</p>
            </div>

            <div class="card">
                <h3>Flujo de Aprobación</h3>
                <p>Monitorea y aprueba expedientes en tu departamento para que avancen en el proceso.</p>
            </div>

            <div class="card">
                <h3>Crear Nuevo Expediente</h3>
                <p>Crea y sube nuevos expedientes para su posterior aprobación dentro del flujo de trabajo.</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
